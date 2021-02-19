package org.tiramisu.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @author felixxfwang
 */
open class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

class ListViewHolder<B: ViewBinding>(val binding: B): BaseViewHolder(binding.root)