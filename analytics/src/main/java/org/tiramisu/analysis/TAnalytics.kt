package org.tiramisu.analysis

import com.google.firebase.analytics.FirebaseAnalytics

/**
 * @author felixxfwang
 */
object TAnalytics : IAnalytics by TFirebaseAnalytics() {

    const val PARAM_METHOD = FirebaseAnalytics.Param.METHOD
}