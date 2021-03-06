package org.tiramisu.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.tiramisu.log.TLog
import java.lang.reflect.ParameterizedType

/**
 * @author felixxfwang
 */
abstract class BaseFragment<BINDING: ViewBinding, VM: ViewModel> : Fragment() {

    companion object {
        private const val TAG = "BaseFragment"
    }

    protected lateinit var binding: BINDING
    protected abstract val vm: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments.first() as Class<*>
                val method = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                binding = method.invoke(null, inflater, container, false) as BINDING
                return binding.root
            } catch (e: Exception) {
                TLog.e(TAG, "view binding inflate failed", e)
            }
        }
        return null
    }

    fun startFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        (activity as? BaseFragmentActivity)?.startFragment(fragment, addToBackStack)
    }

    fun popFragment(addToBackStack: Boolean = false) {
        if (addToBackStack) {
            parentFragmentManager.popBackStack()
        } else {
            (activity as? BaseFragmentActivity)?.popFragment(this)
        }
    }
}