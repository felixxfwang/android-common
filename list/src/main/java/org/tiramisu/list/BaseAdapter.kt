package org.tiramisu.list

import androidx.recyclerview.widget.RecyclerView

/**
 * @author felixxfwang
 */
abstract class BaseAdapter<DATA: BaseData, VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {
    private var data: MutableList<DATA> = ArrayList()
    var onItemClickListener: OnItemClickListener<DATA>? = null

    fun setData(data: List<DATA>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    final override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, data[position])
    }

    abstract fun onBindViewHolder(holder: VH, data: DATA)
}