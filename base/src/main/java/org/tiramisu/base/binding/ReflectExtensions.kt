package org.tiramisu.base.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.reflect.Method

/**
 * @author felixxfwang
 */
const val INFLATE_NAME = "inflate"
const val BIND_NAME = "bind"

fun <T> Class<T>.activityInflateMethod(): Method = getMethod(INFLATE_NAME, LayoutInflater::class.java)

fun <T> Class<T>.fragmentInflateMethod(): Method = getMethod(INFLATE_NAME, LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)

fun <T> Class<T>.bindMethod(): Method = getMethod(BIND_NAME, View::class.java)