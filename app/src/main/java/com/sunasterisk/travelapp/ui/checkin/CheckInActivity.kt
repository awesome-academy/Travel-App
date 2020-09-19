package com.sunasterisk.travelapp.ui.checkin

import androidx.fragment.app.Fragment
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_check_in.*

class CheckInActivity : BaseActivity() {

    override val layoutResource get() = R.layout.activity_check_in

    override fun initComponents() {
        setUpViewPager()
        configureFrontLayout()
    }

    private fun setUpViewPager() {
        viewPagerMainCheckInContent.apply {
            setToolbar(toolbarCheckIn)
            updateListPager(listFragments, listFragmentTitle)
        }
    }

    private fun configureFrontLayout() {
        toolbarCheckIn.post {
            viewPagerMainCheckInContent.setToolbar(toolbarCheckIn)
        }
        materialButtonContinue.post {
            materialButtonContinue.setOnClickListener(viewPagerMainCheckInContent)
        }
    }

    companion object {
        val listFragments = listOf<Fragment>(
            TravelerInforFragment(),
            BookRoomFragment(),
            ConfirmFragment()
        )

        val listFragmentTitle = listOf(
            TravelerInforFragment.TITLE,
            BookRoomFragment.TITLE,
            ConfirmFragment.TITLE
        )
    }
}
