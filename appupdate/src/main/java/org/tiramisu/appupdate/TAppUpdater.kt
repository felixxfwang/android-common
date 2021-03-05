package org.tiramisu.appupdate

import android.app.Application
import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlin.properties.Delegates

object TAppUpdater {

    private var application by Delegates.notNull<Application>()
    private var provider by Delegates.notNull<IUpdateProvider>()

    fun initialize(application: Application, provider: IUpdateProvider) {
        this.application = application
        this.provider = provider
    }

    fun checkAppUpdate(context: Context) {
        provider.fetchAppUpdateData { data -> checkAppUpdate(context, data)}
    }

    private fun checkAppUpdate(context: Context, data: AppUpdateData) {
        if (data.hasUpdate()) {
            showAppUpdateDialog(context, data)
        }
    }

    private fun showAppUpdateDialog(context: Context, data: AppUpdateData) {
        val dialog = AlertDialog.Builder(context).create()
        val prompter = provider.getAppUpdatePrompter() ?: DefaultUpdatePrompter()
        val view = prompter.getPromptView(dialog, data)
        bind(context, dialog, prompter, data)
        dialog.setView(view)
        dialog.show()
    }

    private fun bind(context: Context, dialog: AlertDialog, binding: IUpdatePrompter, data: AppUpdateData) {
        if (data.updateTitle.isNotBlank()) binding.title.text = data.updateTitle
        if (data.updateContent.isNotBlank()) binding.content.text = data.updateContent
        if (data.isForceUpdate()) {
            binding.skipper.visibility = View.GONE
            dialog.setCancelable(false)
        } else {
            binding.skipper.setOnClickListener { dialog.dismiss() }
        }
        binding.updater.setOnClickListener { provider.doAppUpdate(context, data) }
    }
}