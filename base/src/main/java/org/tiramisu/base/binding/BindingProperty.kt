package org.tiramisu.base.binding

import kotlin.properties.ReadOnlyProperty

/**
 * @author felixxfwang
 */
abstract class BindingProperty<T, V>: ReadOnlyProperty<T, V> {
    protected var viewBinding: V? = null

//    protected fun findGenericClass(): Class<*>? {
//        val type = this.javaClass.genericSuperclass
//        if (type is ParameterizedType) {
//            return type.actualTypeArguments.first() as Class<*>
//        }
//        return null
//    }
}