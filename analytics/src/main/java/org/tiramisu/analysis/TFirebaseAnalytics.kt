package org.tiramisu.analysis

import android.app.Application
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

/**
 * @author felixxfwang
 */
class TFirebaseAnalytics: IAnalytics {
    private val analytics: FirebaseAnalytics by lazy { Firebase.analytics }

    override fun initialize(application: Application) {
        FirebaseApp.initializeApp(application)
    }

    override fun traceScreen(screenName: String, screenClass: Class<*>, bundle: Bundle?) {
        val params = bundle ?: Bundle()
        params.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        params.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass.name)
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun logEvent(event: String, bundle: Bundle?) {
        analytics.logEvent(event, bundle)
    }

    override fun logEvent(event: String, block: ParamsBuilder.() -> Unit) {
        val builder = ParamsBuilder().also(block)
        analytics.logEvent(event, builder.bundle)
    }
}