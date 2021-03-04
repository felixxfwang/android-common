package org.tiramisu.config

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class FirebaseRemoteConfig : IRemoteConfig {

    override fun initialize(application: Application, defaultResId: Int) {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 20 else 600
        }
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(configSettings)

            // 设置默认值
            setDefaultsAsync(defaultResId)
        }
    }
}