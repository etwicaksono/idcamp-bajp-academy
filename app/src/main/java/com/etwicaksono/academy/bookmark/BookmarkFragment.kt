package com.etwicaksono.academy.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.etwicaksono.academy.R
import com.etwicaksono.academy.data.CourseEntity
import com.etwicaksono.academy.databinding.FragmentBookmarkBinding
import com.etwicaksono.academy.utils.DataDummy

class BookmarkFragment : Fragment(),BookmarkFragmentCallback{

    lateinit var binding:FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBookmarkBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity!=null){
            val courses =DataDummy.generateDummyCourse()
            val adapter =BookmarkAdapter(this)
            adapter.setCourses(courses)

            with(binding.rvBookmark){
                layoutManager=LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter=adapter
            }
        }
    }

    override fun onShareClick(course: CourseEntity) {
        if(activity!=null){
            val mimeType="text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan aplikasi ini sekarang.")
                .setText(resources.getString(R.string.share_text,course.title))
                .startChooser()
        }
    }
}