package com.etwicaksono.academy.ui.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.etwicaksono.academy.R
import com.etwicaksono.academy.data.source.local.entity.CourseEntity
import com.etwicaksono.academy.databinding.ItemsBookmarkBinding
import com.etwicaksono.academy.ui.detail.DetailCourseActivity

class BookmarkAdapter(private val callback: BookmarkFragmentCallback) :
    PagedListAdapter<CourseEntity, BookmarkAdapter.CourseViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemsBookmarkBinding =
            ItemsBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemsBookmarkBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bind(course)
        }
    }

    fun getSwipedData(swipedPosition: Int): CourseEntity? = getItem(swipedPosition)


    inner class CourseViewHolder(private val binding: ItemsBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: CourseEntity) {
            with(binding) {
                tvItemTitle.text = course.title
                tvItemDate.text =
                    itemView.resources.getString(R.string.deadline_date, course.deadline)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener { callback.onShareClick(course) }
                Glide.with(itemView.context)
                    .load(course.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CourseEntity>() {
            override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
                return oldItem.courseId == newItem.courseId
            }

            override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}
