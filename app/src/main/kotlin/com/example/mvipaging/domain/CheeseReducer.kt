package com.example.mvipaging.domain

import com.arkivanov.mvikotlin.core.store.Reducer
import com.example.mvipaging.domain.CheeseReducer.Result
import com.example.mvipaging.domain.CheeseReducer.Result.NewItems
import com.example.mvipaging.domain.CheeseStore.State
import com.example.mvipaging.presentation.CheeseListItem

class CheeseReducer : Reducer<State, Result> {
    override fun State.reduce(result: Result) = when (result) {
        is NewItems -> copy(items = result.items)
    }

    sealed class Result {
        data class NewItems(val items: List<CheeseListItem?>) : Result()
    }
}
