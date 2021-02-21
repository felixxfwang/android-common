package org.tiramisu.base.binding

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty

/**
 * @author felixxfwang
 */
class FragmentViewBinding<T: ViewBinding>(clazz: Class<T>, private val container: ViewGroup): BindingProperty<Fragment, T>() {

    private var inflater = clazz.fragmentInflateMethod()

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return viewBinding ?: initBiding(thisRef)
    }

    private fun initBiding(thisRef: Fragment): T {
        val bind = inflater.invoke(null, thisRef.layoutInflater, container, false) as T
        return bind.apply { viewBinding = this }
    }
}