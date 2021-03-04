package org.tiramisu.crashlytics

import android.app.Application

interface ICrashlytics {

    fun initialize(application: Application)

    // 缓存64kb
    fun log(tag: String, message: String)
}