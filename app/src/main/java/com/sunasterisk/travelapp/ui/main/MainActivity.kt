package com.sunasterisk.travelapp.ui.main

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.base.BaseActivity
import com.sunasterisk.travelapp.ui.home.HomeFragment
import com.sunasterisk.travelapp.utils.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_home_view.*

class MainActivity : BaseActivity(), HomeFragment.OnTabsSetupListener {

    override val layoutResource get() = R.layout.activity_main

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is HomeFragment) {
            fragment.setTabsCallbackListener(this)
        }
    }

    override fun initComponents() {
        replaceFragmentInActivity(HomeFragment.newInstance(), R.id.frameMain)
        initToolbar()
        initNavigationMenu()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    private fun initNavigationMenu() {
        ActionBarDrawerToggle(
            this,
            drawerMain,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).apply {
            syncState()
        }
        drawerMain.closeDrawer(GravityCompat.START)
    }

    override fun onSetup(viewPager: ViewPager) {
        tabsHome.setupWithViewPager(viewPager)
    }
}
