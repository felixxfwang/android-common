package org.tiramisu.analysis

import android.os.Bundle

interface IAnalytics {

    fun logEvent(event: String, bundle: Bundle? = null)

    fun logEvent(event: String, block: ParamsBuilder.() -> Unit)
}