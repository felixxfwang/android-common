package org.tiramisu.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import org.tiramisu.page.modular.activity.IActivityModularPage
import java.lang.reflect.ParameterizedType

abstract class BaseModularActivity<BINDING: ViewBinding>: BaseActivity<BINDING>(), IActivityModularPage {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        modular.onActivityResult(requestCode, resultCode, data)
    }
}