package org.tiramisu.modular

import org.tiramisu.base.BaseApplication

/**
 * @author felixxfwang
 */
open class ModularApplication: BaseApplication() {
    private val modular = ModularManager()

    override fun onCreate() {
        super.onCreate()

        modular.initialize(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        modular.unload()
    }
}