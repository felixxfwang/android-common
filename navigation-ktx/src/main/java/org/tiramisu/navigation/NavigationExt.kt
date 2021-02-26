package org.tiramisu.navigation

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import org.tiramisu.base.BaseFragmentActivity

/**
 * @author felixxfwang
 */
fun NavController.useFixFragmentNavigator(context: Context, manager: FragmentManager, containerId: Int) {
    val navigator = FixFragmentNavigator(context, manager, containerId)
    navigatorProvider.addNavigator(navigator)
}

fun BaseFragmentActivity.setNavGraph(navHostId: Int, navGraphId: Int) {
    val navController = findNavController(navHostId)
    navController.useFixFragmentNavigator(this, supportFragmentManager, navHostId)
    navController.setGraph(navGraphId)
}