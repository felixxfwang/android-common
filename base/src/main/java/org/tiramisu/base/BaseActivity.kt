package org.tiramisu.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<BINDING: ViewBinding>: AppCompatActivity() {
    protected lateinit var binding: BINDING

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments.first() as Class<*>
                val method = clazz.getMethod("inflate", LayoutInflater::class.java)
                binding = method.invoke(null, layoutInflater) as BINDING
            } catch (e: Exception) {
                e.printStackTrace()
            }
            setContentView(binding.root)
        }

        // 设置沉浸式透明标题栏
        QMUIStatusBarHelper.translucent(this)
    }
}