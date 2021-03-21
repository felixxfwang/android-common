package org.tiramisu.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.commit
import org.tiramisu.base.BaseFragmentActivity
import org.tiramisu.base.binding.viewBind
import org.tiramisu.webview.databinding.ActivityWebviewBinding

/**
 * @author felixxfwang
 */
class WebViewActivity: BaseFragmentActivity() {

    companion object {
        fun start(context: Context, url: String, title: String? = null, showBack: Boolean = true, showOk: Boolean = false) {
            context.startActivity(Intent(context, WebViewActivity::class.java).apply {
                putExtra(WebViewFragment.KEY_URL, url)
                putExtra(WebViewFragment.KEY_TITLE, title)
                putExtra(WebViewFragment.KEY_SHOW_BACK, showBack)
                putExtra(WebViewFragment.KEY_SHOW_OK, showOk)
            })
        }
    }

    private val binding: ActivityWebviewBinding by viewBind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        intent.getStringExtra(WebViewFragment.KEY_TITLE)?.let {
            binding.toolbarTitle.text = it
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
        if (intent.getBooleanExtra(WebViewFragment.KEY_SHOW_BACK, true)) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        supportFragmentManager.commit {
            add(getContainerId(), WebViewFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (intent.getBooleanExtra(WebViewFragment.KEY_SHOW_OK, false)) {
            menuInflater.inflate(R.menu.webview, menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun getContainerId(): Int = R.id.fragment_container

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> onBackButtonPressed()
            R.id.action_confirm -> onBackButtonPressed()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onBackButtonPressed(): Boolean {
        this.finish()
        return true
    }
}