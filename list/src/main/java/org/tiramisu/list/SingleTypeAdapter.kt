package org.tiramisu.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding

/**
 * @author felixxfwang
 */
abstract class SingleTypeAdapter<DATA: BaseData, B: ViewBinding>: BaseAdapter<DATA, ListViewHolder<B>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<B> {
        return ListViewHolder(onCreateBinding(LayoutInflater.from(parent.context)))
    }

    @CallSuper
    override fun onBindViewHolder(holder: ListViewHolder<B>, data: DATA) {
        holder.binding.root.setOnClickListener {
            onItemClickListener?.onItemClick(data)
        }
    }

    abstract fun onCreateBinding(inflater: LayoutInflater): B
}