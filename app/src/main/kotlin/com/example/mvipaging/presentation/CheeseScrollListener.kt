package com.example.mvipaging.presentation

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheeseScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val loadMoreCallback: (Int) -> Unit
) : RecyclerView.OnScrollListener() {
    private var previousEdgeItem = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        previousEdgeItem = if (dy < 0) {
            layoutManager.findFirstVisibleItemPosition()
        } else {
            layoutManager.findLastVisibleItemPosition()
        }.also { currentEdgeItem ->
            if (previousEdgeItem != currentEdgeItem) {
                loadMoreCallback(currentEdgeItem)
            }
        }
    }
}
