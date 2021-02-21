package org.tiramisu.base.binding

import android.app.Activity
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @author felixxfwang
 */
inline fun <reified T: ViewBinding> Activity.viewBind() = ActivityViewBinding(T::class.java)

inline fun <reified T: ViewDataBinding> Activity.viewDataBind() = ActivityViewBinding(T::class.java)

inline fun <reified T: ViewBinding> Fragment.viewBind(container: ViewGroup) = FragmentViewBinding(T::class.java, container)
