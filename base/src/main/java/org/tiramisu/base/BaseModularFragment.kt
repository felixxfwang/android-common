package org.tiramisu.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.tiramisu.page.modular.fragment.IFragmentModularPage

abstract class BaseModularFragment<BINDING: ViewBinding> : BaseFragment<BINDING>(), IFragmentModularPage {

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return modular.onFragmentViewPreCreate(this) ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        modular.onHiddenChanged(hidden)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        modular.onSetUserVisibleHint(isVisibleToUser)
    }
}