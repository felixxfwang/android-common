package org.tiramisu.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination

interface OnDestinationChangedListener {
    fun setActionBarTitle(title: CharSequence)
    fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {}
}