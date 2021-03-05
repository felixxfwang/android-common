package org.tiramisu.appupdate

import android.app.Dialog
import android.view.View
import android.widget.TextView

interface IUpdatePrompter {
    val title: TextView
    val content: TextView
    val updater: View
    val skipper: View
    fun getPromptView(dialog: Dialog, data: AppUpdateData): View
}