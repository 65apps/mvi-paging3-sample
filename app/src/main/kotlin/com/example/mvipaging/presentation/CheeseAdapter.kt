package com.example.mvipaging.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CheeseAdapter : RecyclerView.Adapter<CheeseViewHolder>() {
    var items = emptyList<CheeseListItem?>()
        set(value) {
            field = value
            differ.submitList(value)
        }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CheeseViewHolder(parent)

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.bindTo(items[position])
    }

    override fun getItemCount() = items.size

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CheeseListItem>() {
            override fun areItemsTheSame(oldItem: CheeseListItem, newItem: CheeseListItem) =
                if (oldItem is CheeseListItem.Item && newItem is CheeseListItem.Item) {
                    oldItem.cheese.id == newItem.cheese.id
                } else {
                    oldItem == newItem
                }

            override fun areContentsTheSame(oldItem: CheeseListItem, newItem: CheeseListItem) =
                oldItem == newItem
        }
    }
}
