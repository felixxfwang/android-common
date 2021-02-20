package org.tiramisu.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import org.tiramisu.base.binding.viewBind
import org.tiramisu.base.databinding.ActivityContainerBinding

abstract class ContainerActivity: BaseFragmentActivity() {

    protected val binding: ActivityContainerBinding by viewBind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            add(R.id.container, getFragment())
        }
    }

    abstract fun getFragment(): Fragment
}