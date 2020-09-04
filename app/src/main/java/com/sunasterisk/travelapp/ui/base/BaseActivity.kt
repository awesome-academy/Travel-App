package com.sunasterisk.travelapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initComponents()
        initData()
    }

    protected abstract fun initComponents()

    protected abstract fun initData()

    protected fun openFragment(id: Int, fragment: Fragment, addToBackStack: Boolean = false) =
        supportFragmentManager.beginTransaction().replace(id, fragment).apply {
            if (addToBackStack) addToBackStack(null)
        }.commit()
}

