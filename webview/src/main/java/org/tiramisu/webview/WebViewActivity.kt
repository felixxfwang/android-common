package org.tiramisu.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.commit
import org.tiramisu.base.BaseFragmentActivity
import org.tiramisu.base.binding.viewBind
import org.tiramisu.webview.databinding.ActivityWebviewBinding

/**
 * @author felixxfwang
 */
class WebViewActivity: BaseFragmentActivity() {

    companion object {
        fun start(context: Context, url: String) {
            context.startActivity(Intent(context, WebViewActivity::class.java).apply {
                putExtra(WebViewFragment.KEY_URL, url)
            })
        }
    }

    private val binding: ActivityWebviewBinding by viewBind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            add(getContainerId(), WebViewFragment())
        }
        binding.toolbar
    }

    override fun getContainerId(): Int = R.id.fragment_container
}