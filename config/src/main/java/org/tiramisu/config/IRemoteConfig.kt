package org.tiramisu.config

import android.app.Application

/**
 * @author felixxfwang
 */
interface IRemoteConfig {

    fun initialize(application: Application)
}