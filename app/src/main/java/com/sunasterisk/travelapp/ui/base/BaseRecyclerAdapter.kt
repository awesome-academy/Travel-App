package com.sunasterisk.travelapp.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, V : BaseViewHolder<T>> : RecyclerView.Adapter<V>() {

    private val items = mutableListOf<T>()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: V, position: Int) {
        getItem(position)?.let {
            holder.onBindData(it)
        }
    }

    protected fun getItem(position: Int): T? =
        if (position in 0 until itemCount) items[position] else null

    fun updateData(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
