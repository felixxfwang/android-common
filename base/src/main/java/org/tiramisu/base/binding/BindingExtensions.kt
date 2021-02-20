package org.tiramisu.base.binding

import android.app.Activity
import androidx.viewbinding.ViewBinding

/**
 * @author felixxfwang
 */
inline fun <reified T: ViewBinding> Activity.viewBind() = ActivityViewBinding(T::class.java)