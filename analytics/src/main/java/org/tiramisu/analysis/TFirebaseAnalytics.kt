package org.tiramisu.analysis

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

/**
 * @author felixxfwang
 */
class TFirebaseAnalytics: IAnalytics {
    private val analytics: FirebaseAnalytics by lazy { Firebase.analytics }

    override fun logEvent(event: String, bundle: Bundle?) {
        analytics.logEvent(event, bundle)
    }

    override fun logEvent(event: String, block: ParamsBuilder.() -> Unit) {
        val builder = ParamsBuilder().also(block)
        analytics.logEvent(event, builder.bundle)
    }
}