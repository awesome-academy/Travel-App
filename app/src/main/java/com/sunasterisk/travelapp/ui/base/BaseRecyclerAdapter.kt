package com.sunasterisk.travelapp.ui.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, V : BaseViewHolder<T>> : RecyclerView.Adapter<V>() {

    private val items = mutableListOf<T>()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: V, position: Int) {
        getItem(position)?.let { viewHolder.onBindData(it) }
    }

    protected fun getItem(position: Int): T? =
        if (position in 0 until itemCount) items[position] else null

    fun updateData(newItems: List<T>) {
        val diffCallback = DiffUtilCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.run{
            clear()
            addAll(newItems)
        }
        diffResult.dispatchUpdatesTo(this)
    }

    fun insertData(insertItems: List<T>) = with(items) {
        val firstPosition = size
        addAll(insertItems)
        val secondPosition = size
        notifyItemRangeInserted(firstPosition, secondPosition - 1)
    }

    fun removeData(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }
}


