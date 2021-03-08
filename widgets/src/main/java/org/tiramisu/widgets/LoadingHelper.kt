package org.tiramisu.widgets

import android.content.Context
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog

/**
 * @author felixxfwang
 */
class LoadingHelper(private val context: Context, private val loadingText: String) {

    private val loadingTip by lazy {
        QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(loadingText)
                .create()
    }

    fun showLoading() = loadingTip.show()

    fun hideLoading() = loadingTip.hide()
}