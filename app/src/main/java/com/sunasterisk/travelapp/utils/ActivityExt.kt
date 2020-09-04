package com.sunasterisk.travelapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.replaceFragment(
    id: Int, fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String? = null
) {
    supportFragmentManager.beginTransaction().replace(id, fragment).apply {
        if (addToBackStack) addToBackStack(tag)
    }.commit()
}

fun FragmentActivity.addFragment(
    id: Int, fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String? = null
) {
    supportFragmentManager.beginTransaction().add(id, fragment).apply {
        if (addToBackStack) addToBackStack(tag)
    }.commit()
}

