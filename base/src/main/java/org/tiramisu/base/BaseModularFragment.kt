package org.tiramisu.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.tiramisu.page.modular.fragment.IFragmentModularPage

abstract class BaseModularFragment<BINDING: ViewBinding, VM: ViewModel>
    : BaseFragment<BINDING, VM>(), IFragmentModularPage<BINDING, VM> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInitializePageModules()
    }

    abstract fun onInitializePageModules()

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return modular.onFragmentViewPreCreate(this) ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        modular.onHiddenChanged(hidden)
    }

    override fun binding(): BINDING = binding

    override fun viewModel(): VM = vm

    override fun fragment(): Fragment = this

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        modular.onSetUserVisibleHint(isVisibleToUser)
    }
}