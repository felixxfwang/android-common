package org.tiramisu.appupdate

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import org.tiramisu.appupdate.databinding.DialogAppUpdateBinding
import kotlin.properties.Delegates

object TAppUpdater {

    private var application by Delegates.notNull<Application>()
    private var provider by Delegates.notNull<IAppUpdateProvider>()

    fun initialize(application: Application, provider: IAppUpdateProvider) {
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
        dialog.setView(provider.buildPromptView(dialog, data) ?: buildDialogView(context, dialog, data))
        dialog.show()
    }

    private fun buildDialogView(context: Context, dialog: AlertDialog, data: AppUpdateData): View {
        val binding = DialogAppUpdateBinding.inflate(LayoutInflater.from(context))
        if (data.updateTitle.isNotBlank()) binding.title.text = data.updateTitle
        if (data.updateContent.isNotBlank()) binding.content.text = data.updateContent
        if (data.isForceUpdate()) {
            binding.skip.visibility = View.GONE
            dialog.setCancelable(false)
        } else {
            binding.skip.setOnClickListener { dialog.dismiss() }
        }
        binding.update.setOnClickListener { provider.doAppUpdate(context, data) }
        return binding.root
    }
}