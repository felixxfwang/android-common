package org.tiramisu.appupdate

import com.blankj.utilcode.util.AppUtils
import kotlin.math.max

/**
 * @author felixxfwang
 */
data class AppUpdateData(
    val minAppVersion: Int,
    val latestAppVersion: Int,
    val updateTitle: String = "",
    val updateContent: String = ""
)


val AppUpdateData.targetVerCode: Int get() = max(minAppVersion, latestAppVersion)

fun AppUpdateData.isForceUpdate() = minAppVersion > AppUtils.getAppVersionCode()

fun AppUpdateData.hasUpdate() = latestAppVersion > AppUtils.getAppVersionCode()