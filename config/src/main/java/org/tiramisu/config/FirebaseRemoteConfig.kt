package org.tiramisu.config

import android.app.Application
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.tiramisu.log.TLog
import java.util.concurrent.atomic.AtomicBoolean

class FirebaseRemoteConfig : IRemoteConfig {

    companion object {
        private const val TAG = "FirebaseRemoteConfig"
    }

    private var task: Task<Boolean>? = null
    private val fetched = AtomicBoolean(false)

    override fun initialize(application: Application, defaultResId: Int) {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 20 else 600
        }
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(configSettings)

            // 设置默认值
            setDefaultsAsync(defaultResId)
            task = fetchAndActivate().addOnCompleteListener {
                TLog.i(TAG, "remote config fetch result: isSuccess=${it.isSuccessful}, exception=${it.exception}")
                fetched.set(true)
                task = null
            }
        }
    }

    override fun doWhenFetchActivated(action: () -> Unit) {
        val listener = { _: Task<Boolean> -> action.invoke() }
        task?.addOnCompleteListener(listener)
        if (fetched.get()) {
            TLog.i(TAG, "remote config fetched, do action directly.")
            action.invoke()
        } else {
            TLog.i(TAG, "remote config not fetched, do action when fetched.")
        }
    }
}