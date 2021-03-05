package org.tiramisu.appupdate

import android.content.Context
import com.blankj.utilcode.util.AppUtils
import org.tiramisu.utils.AppMarketUtil

interface IUpdateProvider {

    fun fetchAppUpdateData(receiver: (AppUpdateData) -> Unit)

    fun getAppUpdatePrompter(): IUpdatePrompter? = null

    /**
     * 执行更新操作，默认跳GooglePlay
     */
    fun doAppUpdate(context: Context, data: AppUpdateData) {
        AppMarketUtil.openGooglePlay(context, AppUtils.getAppPackageName())
    }
}