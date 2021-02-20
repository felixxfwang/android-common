package org.tiramisu.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import org.tiramisu.base.navigation.useFixFragmentNavigator

abstract class BaseFragmentActivity<BINDING: ViewBinding>(
    private val containerId: Int = 0
): BaseActivity<BINDING>() {

    protected abstract fun getContainerId(): Int

    private var currentFragment: Fragment? = null
    private var lastFragment: Fragment? = null

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

    fun setNavGraph(navGraphId: Int) {
        val navController = findNavController(getContainerId())
        navController.useFixFragmentNavigator(this, supportFragmentManager, getContainerId())
        navController.setGraph(navGraphId)
    }
}