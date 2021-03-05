package org.tiramisu.appupdate

import android.app.Dialog
import android.content.Context
import android.view.View
import com.blankj.utilcode.util.AppUtils
import org.tiramisu.utils.AppMarketUtil

interface IAppUpdateProvider {

    fun fetchAppUpdateData(receiver: (AppUpdateData) -> Unit)

    fun buildPromptView(dialog: Dialog, data: AppUpdateData): View? = null

    /**
     * 执行更新操作，默认跳GooglePlay
     */
    fun doAppUpdate(context: Context, data: AppUpdateData) {
        AppMarketUtil.openGooglePlay(context, AppUtils.getAppPackageName())
    }
}