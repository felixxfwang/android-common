package org.tiramisu.appupdate

import android.app.Dialog
import android.view.View
import android.widget.TextView
import org.tiramisu.appupdate.databinding.DialogAppUpdateBinding
import kotlin.properties.Delegates

/**
 * @author felixxfwang
 */
class DefaultUpdatePrompter: IUpdatePrompter {
    private var binding by Delegates.notNull<DialogAppUpdateBinding>()

    override val title: TextView get() = binding.title

    override val content: TextView get() = binding.content

    override val updater: View get() = binding.update

    override val skipper: View get() = binding.skip

    override fun getPromptView(dialog: Dialog, data: AppUpdateData): View {
        binding = DialogAppUpdateBinding.inflate(dialog.layoutInflater)
        return binding.root
    }
}