package org.tiramisu.list

import androidx.recyclerview.widget.RecyclerView

/**
 * @author felixxfwang
 */
abstract class BaseAdapter<DATA: BaseData, VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {
    private var data: MutableList<DATA> = ArrayList()
    var onItemClickListener: OnItemClickListener? = null

    fun setDataList(data: List<DATA>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getDataList(): List<DATA> = data

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val data = getDataList()[position]
        holder.itemView.setOnClickListener {
            onItemClick(holder, position, data)
            onItemClickListener?.onItemClick(holder.itemView, position, data)
        }
        onBindViewHolder(holder, data)
    }

    open fun onItemClick(holder: VH, position: Int, data: BaseData) {}

    abstract fun onBindViewHolder(holder: VH, data: DATA)
}