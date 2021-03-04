package org.tiramisu.config

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class FirebaseRemoteConfig : IRemoteConfig {

    override fun initialize(application: Application) {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 600
        }
        Firebase.remoteConfig.setConfigSettingsAsync(configSettings)
    }
}