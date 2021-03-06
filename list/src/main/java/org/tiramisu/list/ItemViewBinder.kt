package org.tiramisu.list

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * @author felixxfwang
 */
abstract class ItemViewBinder<BINDING: ViewBinding, DATA: BaseData>: IItemViewBinder {

    @Suppress("UNCHECKED_CAST")
    override fun onBindView(binding: ViewBinding, data: BaseData) {
        if (canBindView(binding, data)) {
            onBindingView(binding as BINDING, data as DATA)
        }
    }

    override fun canBindView(binding: ViewBinding, data: BaseData): Boolean {
//        val type = this.javaClass.genericSuperclass
//        if (type is ParameterizedType) {
//            val bindingClazz = type.actualTypeArguments.firstOrNull()
//            val dataClazz = type.actualTypeArguments.getOrNull(1)
//            return binding.javaClass == bindingClazz && data.javaClass == dataClazz
//        }
        return true
    }

    abstract fun onBindingView(binding: BINDING, data: DATA)

    @Suppress("UNCHECKED_CAST")
    override fun onItemViewClick(binding: ViewBinding, position: Int, data: BaseData) {
        onItemClick(binding as BINDING, position, data as DATA)
    }

    open fun onItemClick(binding: BINDING, position: Int, data: DATA) {}
}

class DefaultItemViewBinder: IItemViewBinder {
    override val viewType: Int = Int.MIN_VALUE

    override fun onCreateBinding(inflater: LayoutInflater): ViewBinding = ViewBinding { View(inflater.context) }

    override fun onBindView(binding: ViewBinding, data: BaseData) {}

    override fun canBindView(binding: ViewBinding, data: BaseData): Boolean = false

    override fun onItemViewClick(binding: ViewBinding, position: Int, data: BaseData) {}
}