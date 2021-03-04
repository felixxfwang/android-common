package org.tiramisu.crashlytics

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics

/**
 * @author felixxfwang
 */
class TFirebaseCrashlytics: ICrashlytics {
    
    override fun initialize(application: Application) {
        setCustomKey("TFirebaseCrashlytics", true)
    }

    override fun log(tag: String, message: String) {
        FirebaseCrashlytics.getInstance().log("$tag:$message")
    }

    override fun setCustomKey(key: String, value: Boolean) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun setCustomKey(key: String, value: Int) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun setCustomKey(key: String, value: String) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun setCustomKey(key: String, value: Long) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun setCustomKey(key: String, value: Float) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun setCustomKey(key: String, value: Double) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }
}