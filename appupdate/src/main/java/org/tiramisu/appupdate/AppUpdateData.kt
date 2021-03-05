package org.tiramisu.appupdate

import com.blankj.utilcode.util.AppUtils
import kotlin.math.max

/**
 * @author felixxfwang
 */
data class AppUpdateData(
    val min_app_version: Int = 0,
    val latest_app_version: Int = 0,
    val title: String? = null,
    val content: String? = null
)

val AppUpdateData.targetVerCode: Int get() = max(min_app_version, latest_app_version)

fun AppUpdateData.isForceUpdate() = min_app_version > AppUtils.getAppVersionCode()

fun AppUpdateData.hasUpdate() = latest_app_version > AppUtils.getAppVersionCode()