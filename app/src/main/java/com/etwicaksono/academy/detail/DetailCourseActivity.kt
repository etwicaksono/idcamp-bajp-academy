package com.etwicaksono.academy.detail

import android.content.Intent
import android.os.Bundle
import android.view.RoundedCorner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.etwicaksono.academy.CourseReaderActivity
import com.etwicaksono.academy.R
import com.etwicaksono.academy.data.CourseEntity
import com.etwicaksono.academy.databinding.ActivityDetailCourseBinding
import com.etwicaksono.academy.databinding.ContentDetailCourseBinding
import com.etwicaksono.academy.utils.DataDummy

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var detailCourseBinding: ActivityDetailCourseBinding
    private lateinit var detailContentBinding: ContentDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = detailCourseBinding.detailContent

        setContentView(detailCourseBinding.root)

        setSupportActionBar(detailCourseBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter=DetailCourseAdapter()

        val extras = intent.extras
        if(extras!=null){
            val courseId=extras.getString(EXTRA_COURSE)
            if(courseId!=null){
                val modules=DataDummy.generateDummyModules(courseId)
                adapter.setModules(modules)
                for(course in DataDummy.generateDummyCourse()){
                    if(course.courseId==courseId){
                        populateCourse(course)
                    }
                }
            }
        }

        with(detailContentBinding.rvModule){
            isNestedScrollingEnabled=false
            layoutManager=LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter=adapter
            val dividerItemDecoration=DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(course: CourseEntity) {
        detailContentBinding.apply {
            tvTitle.text=course.title
            tvDescription.text=course.description
            tvDate.text=course.deadline

            Glide.with(this@DetailCourseActivity)
                .load(course.imagePath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(detailContentBinding.ivPoster)

            btnStart.setOnClickListener {
                val intent=Intent(this@DetailCourseActivity,CourseReaderActivity::class.java)
                intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID,course.courseId)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}