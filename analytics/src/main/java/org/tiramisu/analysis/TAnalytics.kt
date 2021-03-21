package org.tiramisu.analysis

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author felixxfwang
 */
private var analytics: IAnalytics = TFirebaseAnalytics()

object TAnalytics : IAnalytics by analytics {

    const val PARAM_METHOD = FirebaseAnalytics.Param.METHOD

    private val isInitialized = AtomicBoolean(false)

    fun isInitialized() = isInitialized.get()

    @Synchronized
    override fun initialize(application: Application, userProperties: Map<String, String>?) {
        if (isInitialized.compareAndSet(false, true)) {
            analytics.initialize(application, userProperties)
            application.registerActivityLifecycleCallbacks(TAnalyticsLifecycleCallbacks())
        }
    }
}