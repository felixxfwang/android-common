package org.tiramisu.analysis

import android.app.Application
import android.os.Bundle

interface IAnalytics {

    fun initialize(application: Application)

    fun logEvent(event: String, bundle: Bundle? = null)

    fun logEvent(event: String, block: ParamsBuilder.() -> Unit)
}