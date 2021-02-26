package org.tiramisu.list

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

interface IItemViewBinder {
    val viewType: Int
    fun onCreateBinding(inflater: LayoutInflater): ViewBinding
    fun onBindView(binding: ViewBinding, data: BaseData)
    fun canBindView(binding: ViewBinding, data: BaseData): Boolean

    fun onItemViewClick(binding: ViewBinding, position: Int, data: BaseData)
}