package org.tiramisu.config

import android.app.Application
import android.os.Bundle

/**
 * @author felixxfwang
 */
interface IRemoteConfig {

    fun initialize(application: Application, defaultResId: Int)

    fun doWhenFetchActivated(action: (IKeyValueQuerier) -> Unit)

    fun setDebugConfig(bundle: Bundle)
}