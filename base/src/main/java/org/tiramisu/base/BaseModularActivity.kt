package org.tiramisu.base

import android.content.Intent
import org.tiramisu.page.modular.activity.IActivityModularPage

abstract class BaseModularActivity: BaseActivity(), IActivityModularPage {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        modular.onActivityResult(requestCode, resultCode, data)
    }
}