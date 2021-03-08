package org.tiramisu.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import org.tiramisu.base.BaseFragment
import org.tiramisu.webview.databinding.FragmentWebviewBinding

/**
 * @author felixxfwang
 */
class WebViewFragment: BaseFragment<FragmentWebviewBinding, WebViewModel>() {

    companion object {
        const val KEY_URL = "url"
        const val KEY_TITLE = "title"
        const val KEY_SHOW_BACK = "show_back"
    }

    override val vm: WebViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                view.loadUrl(request.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        getUrl()?.let { url ->
            binding.webview.loadUrl(url)
        }
    }

    private fun getUrl(): String? {
        return arguments?.getString(KEY_URL) ?: activity?.intent?.getStringExtra(KEY_URL)
    }
}