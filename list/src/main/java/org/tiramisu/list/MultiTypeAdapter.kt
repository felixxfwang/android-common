package org.tiramisu.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.viewbinding.ViewBinding

/**
 * @author felixxfwang
 */
class MultiTypeAdapter: BaseAdapter<BaseData, ListViewHolder<ViewBinding>>() {

    private val registry = SparseArrayCompat<IItemViewBinder>()
    private val defaultBinder = DefaultItemViewBinder()

    fun register(type: Int, binder: IItemViewBinder) {
        registry.put(type, binder)
    }

    private fun get(type: Int): IItemViewBinder = registry.get(type) ?: defaultBinder

    override fun getItemViewType(position: Int): Int {
        return getDataList()[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<ViewBinding> {
        return ListViewHolder(get(viewType).onCreateBinding(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ListViewHolder<ViewBinding>, position: Int) {
        val data = getDataList()[position]
        holder.itemView.setOnClickListener { get(data.type).onItemViewClick(data) }
        super.onBindViewHolder(holder, position)
    }

    override fun onBindViewHolder(holder: ListViewHolder<ViewBinding>, data: BaseData) {
        get(data.type).onBindView(holder.binding, data)
    }
}