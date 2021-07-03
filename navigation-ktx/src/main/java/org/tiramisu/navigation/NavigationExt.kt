package org.tiramisu.navigation

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDirections
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

/**
 * fix NavController.navigate() 多次连续调用会crash的问题
 * https://console.firebase.google.com/u/2/project/nicevpn-3530c/crashlytics/app/android:com.neostudio.secret/issues/1ef6b8bfcd22b5be5929dc4b0cf53a8b?time=last-thirty-days&versions=1.1.3%20(5)&sessionEventKey=60DD5DCD017F000106DCB7034E1AAB68_1558283782984812258
 *
 * Fatal Exception: java.lang.IllegalArgumentException
 * Navigation action/destination com.neostudio.secret:id/action_vpnFragment_to_regionListFragment
 * cannot be found from the current destination Destination(com.neostudio.secret:id/regionListFragment)
 * label=Server Location class=com.neostudio.nice.region.RegionListFragment
 *
 * 解析文章：https://nezspencer.medium.com/navigation-components-a-fix-for-navigation-action-cannot-be-found-in-the-current-destination-95b63e16152e
 */
fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}