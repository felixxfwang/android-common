package org.tiramisu.analysis

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * @author felixxfwang
 */
private var analytics: IAnalytics = TFirebaseAnalytics()

object TAnalytics : IAnalytics by analytics {

    const val PARAM_METHOD = FirebaseAnalytics.Param.METHOD

    override fun initialize(application: Application, userProperties: Map<String, String>?) {
        analytics.initialize(application, userProperties)
        application.registerActivityLifecycleCallbacks(TAnalyticsLifecycleCallbacks())
    }
}