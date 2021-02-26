package org.tiramisu.list

import android.view.View

interface OnItemClickListener {
    fun onItemClick(view: View, position: Int, data: BaseData)
}