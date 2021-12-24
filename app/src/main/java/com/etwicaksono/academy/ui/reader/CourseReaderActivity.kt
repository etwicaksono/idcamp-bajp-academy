package com.etwicaksono.academy.ui.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.etwicaksono.academy.R
import com.etwicaksono.academy.ui.reader.content.ModuleContentFragment
import com.etwicaksono.academy.ui.reader.list.ModuleListFragment

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        val viewModel=ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]

        val bundle = intent.extras
        if (bundle != null) {
            val courseId = bundle.getString(EXTRA_COURSE_ID)
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId)
                populateFragment()
            }
        }
    }

    private fun populateFragment() {
        val fragmetTransaction=supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
        if(fragment==null){
            fragment=ModuleListFragment.newInstance()
            fragmetTransaction.add(R.id.frame_container,fragment,ModuleListFragment.TAG)
            fragmetTransaction.addToBackStack(null)
        }
        fragmetTransaction.commit()
    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment = ModuleContentFragment.newInstace()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_container, fragment, ModuleContentFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }
}