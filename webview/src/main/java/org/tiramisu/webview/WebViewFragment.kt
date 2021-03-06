package org.tiramisu.webview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.tiramisu.base.BaseFragment
import org.tiramisu.webview.databinding.FragmentWebviewBinding

/**
 * @author felixxfwang
 */
class WebViewFragment: BaseFragment<FragmentWebviewBinding, WebViewModel>() {

    companion object {
        const val KEY_URL = "url"
    }

    override val vm: WebViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString(KEY_URL)?.let { url ->
            binding.webview.loadUrl(url)
        }
    }
}