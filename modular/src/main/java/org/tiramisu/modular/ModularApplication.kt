package org.tiramisu.modular

import android.content.Context
import org.tiramisu.base.BaseApplication

/**
 * @author felixxfwang
 */
open class ModularApplication: BaseApplication() {
    private val modular = ModularManager()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        modular.onAttachBaseContext(this)
    }

    override fun onCreate() {
        super.onCreate()

        modular.initialize(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        modular.unload()
    }
}