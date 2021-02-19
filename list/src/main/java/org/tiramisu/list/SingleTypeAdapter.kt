package org.tiramisu.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * @author felixxfwang
 */
abstract class SingleTypeAdapter<DATA: BaseData, B: ViewBinding>: BaseAdapter<DATA, ListViewHolder<B>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<B> {
        return ListViewHolder(onCreateBinding(LayoutInflater.from(parent.context)))
    }

    abstract fun onCreateBinding(inflater: LayoutInflater): B
}