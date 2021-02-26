package org.tiramisu.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController

abstract class BaseFragmentActivity(
    private val containerId: Int = 0
): BaseActivity() {

    protected abstract fun getContainerId(): Int

    private var currentFragment: Fragment? = null
    private var lastFragment: Fragment? = null

    protected val navController: NavController by lazy { findNavController(getContainerId()) }

    fun startFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        supportFragmentManager.commit {
            if (addToBackStack) {
                addToBackStack(null)
            }
            val current = currentFragment
            if (current != null) {
                hide(current)
            }
            add(getContainerId(), fragment)
        }
        lastFragment = currentFragment
        currentFragment = fragment
    }

    fun popFragment(fragment: Fragment? = null) {
        if (fragment == null) {
            supportFragmentManager.popBackStack()
            lastFragment?.let {
                supportFragmentManager.commit { show(it) }
            }
        } else {
            supportFragmentManager.commit {
                lastFragment?.let { show(it) }
                remove(fragment)
            }
        }
        currentFragment = lastFragment
        lastFragment = null
    }
}