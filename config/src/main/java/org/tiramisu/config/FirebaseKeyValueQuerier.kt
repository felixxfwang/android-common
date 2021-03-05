package org.tiramisu.config

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import com.google.firebase.remoteconfig.ktx.remoteConfig

class FirebaseKeyValueQuerier : IKeyValueQuerier {
    override fun getString(key: String): String {
        return Firebase.remoteConfig.getString(key)
    }

    override fun getBoolean(key: String): Boolean {
        return Firebase.remoteConfig.getBoolean(key)
    }

    override fun getInt(key: String): Int {
        return Firebase.remoteConfig.getLong(key).toInt()
    }

    override fun getValue(key: String): IConfigValue {
        return ConfigValueWrapper(Firebase.remoteConfig.getValue(key))
    }
}

class ConfigValueWrapper(private val value: FirebaseRemoteConfigValue): IConfigValue {
    override fun asLong(): Long = value.asLong()

    override fun asDouble(): Double = value.asDouble()

    override fun asString(): String = value.asString()

    override fun asByteArray(): ByteArray = value.asByteArray()

    override fun asBoolean(): Boolean = value.asBoolean()

    override fun getSource(): Int = value.source
}