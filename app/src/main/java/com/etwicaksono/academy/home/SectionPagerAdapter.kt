package com.etwicaksono.academy.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.etwicaksono.academy.R
import com.etwicaksono.academy.academy.AcademyFragment
import com.etwicaksono.academy.bookmark.BookmarkFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.home, R.string.bookmark)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AcademyFragment()
            1 -> BookmarkFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}