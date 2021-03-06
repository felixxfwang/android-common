package org.tiramisu.analysis

import android.app.Application
import android.os.Bundle

interface IAnalytics {

    fun initialize(application: Application, userProperties: Map<String, String>? = null)

    fun setUerProperties(properties: Map<String, String>)

    fun traceScreen(screenName: String, screenClass: Class<*>, bundle: Bundle? = null)

    fun logEvent(event: String, bundle: Bundle? = null)

    fun logEvent(event: String, block: ParamsBuilder.() -> Unit)
}