package org.tiramisu.analysis

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * @author felixxfwang
 */
var analytics: IAnalytics = TFirebaseAnalytics()

object TAnalytics : IAnalytics by analytics {

    const val PARAM_METHOD = FirebaseAnalytics.Param.METHOD

    override fun initialize(application: Application) {
        analytics.initialize(application)
        application.registerActivityLifecycleCallbacks(TAnalyticsLifecycleCallbacks())
    }
}