package com.example.mvipaging.presentation

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.example.mvipaging.R
import com.example.mvipaging.domain.CheeseStore.Intent
import com.example.mvipaging.domain.CheeseStore.Intent.LoadMore
import com.example.mvipaging.domain.CheeseStore.State

class CheeseViewImpl(
    view: View
) : BaseMviView<State, Intent>(), CheeseView {
    private val recyclerView: RecyclerView = view.findViewById(R.id.cheeseList)
    private val adapter = CheeseAdapter()

    init {
        val layoutManager = LinearLayoutManager(view.context)
        val scrollListener = CheeseScrollListener(layoutManager) {
            dispatch(LoadMore(it))
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(scrollListener)
    }

    override fun render(model: State) {
        adapter.items = model.items
    }
}
