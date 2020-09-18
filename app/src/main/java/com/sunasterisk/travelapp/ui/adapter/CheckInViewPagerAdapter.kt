package com.sunasterisk.travelapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CheckInViewPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = mutableListOf<Fragment>()
    private val fragmentTitles = mutableListOf<String>()

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    fun updateList(fragments: List<Fragment>) {
        this.fragments.run {
            clear()
            addAll(fragments)
        }
        notifyDataSetChanged()
    }

    fun updateTitles(titles: List<String>) {
        this.fragmentTitles.run {
            clear()
            addAll(titles)
        }
    }

    fun getTitle(position: Int) = fragmentTitles[position]
}
