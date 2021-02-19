package org.tiramisu.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding
import org.tiramisu.base.databinding.ActivityProfileBinding

abstract class ContainerActivity: BaseActivity<ActivityProfileBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            add(R.id.container, getFragment())
        }
    }

    abstract fun getFragment(): Fragment
}