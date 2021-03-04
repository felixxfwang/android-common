package org.tiramisu.crashlytics

import android.app.Application

interface ICrashlytics {

    fun initialize(application: Application)

    // 缓存64kb
    fun log(tag: String, message: String)


    fun setCustomKey(key: String, value: Boolean)
    fun setCustomKey(key: String, value: Int)
    fun setCustomKey(key: String, value: String)
    fun setCustomKey(key: String, value: Long)
    fun setCustomKey(key: String, value: Float)
    fun setCustomKey(key: String, value: Double)
}