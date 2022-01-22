package com.etwicaksono.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.etwicaksono.academy.R
import com.etwicaksono.academy.data.source.local.entity.CourseEntity
import com.etwicaksono.academy.databinding.ActivityDetailCourseBinding
import com.etwicaksono.academy.databinding.ContentDetailCourseBinding
import com.etwicaksono.academy.ui.reader.CourseReaderActivity
import com.etwicaksono.academy.viewmodel.ViewModelFactory
import com.etwicaksono.academy.vo.Status

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var detailCourseBinding: ActivityDetailCourseBinding
    private lateinit var detailContentBinding: ContentDetailCourseBinding
    private lateinit var viewModel: DetailCourseViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = detailCourseBinding.detailContent

        setContentView(detailCourseBinding.root)

        setSupportActionBar(detailCourseBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailCourseViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId)

                viewModel.courseModule.observe(this, { courseWithModuleResource ->
                    if (courseWithModuleResource != null) {
                        when (courseWithModuleResource.status) {
                            Status.LOADING -> detailCourseBinding.progressBar.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> if (courseWithModuleResource.data != null) {
                                detailCourseBinding.apply {
                                    progressBar.visibility = View.GONE
                                    content.visibility = View.VISIBLE
                                }

                                adapter.apply {
                                    setModules(courseWithModuleResource.data.mModules)
                                    notifyDataSetChanged()
                                }
                                populateCourse(courseWithModuleResource.data.mCourse)
                            }
                            Status.ERROR -> {
                                detailCourseBinding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
            }
        }

        with(detailContentBinding.rvModule) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration =
                DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(course: CourseEntity) {
        detailContentBinding.apply {
            tvTitle.text = course.title
            tvDescription.text = course.description
            tvDate.text = course.deadline

            Glide.with(this@DetailCourseActivity)
                .load(course.imagePath)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(detailContentBinding.ivPoster)

            btnStart.setOnClickListener {
                val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
                intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, course.courseId)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        this.menu=menu
        viewModel.courseModule.observe(this,{courseWithModule->
            if(courseWithModule!=null){
                when(courseWithModule.status){
                    Status.LOADING->detailCourseBinding.progressBar.visibility=View.VISIBLE
                    Status.SUCCESS->if(courseWithModule.data!=null){
                        detailCourseBinding.progressBar.visibility=View.GONE
                        val state=courseWithModule.data.mCourse.bookmarked
                        setBookmarkState(state)
                    }
                    Status.ERROR->{
                        detailCourseBinding.progressBar.visibility=View.GONE
                        Toast.makeText(applicationContext,"Terjadi kesalahan",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.action_bookmark){
            viewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem=menu?.findItem(R.id.action_bookmark)
        if(state){
            menuItem?.icon=ContextCompat.getDrawable(this,R.drawable.ic_bookmarked_white)
        }else{
            menuItem?.icon=ContextCompat.getDrawable(this,R.drawable.ic_bookmark_white)
        }
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}