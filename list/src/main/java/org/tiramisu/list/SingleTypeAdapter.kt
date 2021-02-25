package org.tiramisu.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * @author felixxfwang
 */
abstract class SingleTypeAdapter<DATA: BaseData, B: ViewBinding>: BaseAdapter<DATA, ListViewHolder<B>>() {

    var onItemClickListener: OnItemClickListener<DATA>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<B> {
        return ListViewHolder(onCreateBinding(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ListViewHolder<B>, position: Int) {
        val data = getDataList()[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(data)
        }
        super.onBindViewHolder(holder, position)
    }

    abstract fun onCreateBinding(inflater: LayoutInflater): B
}