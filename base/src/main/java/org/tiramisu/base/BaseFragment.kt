package org.tiramisu.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author felixxfwang
 */
abstract class BaseFragment<BINDING: ViewBinding> : Fragment() {

    protected lateinit var binding: BINDING

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments.first() as Class<*>
                val method = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                binding = method.invoke(inflater, container, false) as BINDING
                return binding.root
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        onInitialize(binding)
        return null
    }

    abstract fun onInitialize(binding: BINDING)
}