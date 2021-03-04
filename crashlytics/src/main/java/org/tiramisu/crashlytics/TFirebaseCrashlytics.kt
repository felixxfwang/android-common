package org.tiramisu.crashlytics

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics

/**
 * @author felixxfwang
 */
class TFirebaseCrashlytics: ICrashlytics {
    override fun initialize(application: Application) {
    }

    override fun log(tag: String, message: String) {
        FirebaseCrashlytics.getInstance().log("$tag:$message")
    }
}