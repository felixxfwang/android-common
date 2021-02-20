package org.tiramisu.base.binding

import android.app.Activity
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty

/**
 * @author felixxfwang
 */
class ActivityViewBinding<T: ViewBinding>(clazz: Class<T>): BindingProperty<Activity, T>() {

    private var inflater = clazz.inflateMethod()

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return viewBinding ?: initBiding(thisRef)
    }

    private fun initBiding(thisRef: Activity): T {
        val bind = inflater.invoke(null, thisRef.layoutInflater) as T
        thisRef.setContentView(bind.root)
        return bind.apply { viewBinding = this }
    }
}