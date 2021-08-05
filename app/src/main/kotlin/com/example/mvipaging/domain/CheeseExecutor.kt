package com.example.mvipaging.domain

import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.example.mvipaging.domain.CheeseReducer.Result
import com.example.mvipaging.domain.CheeseStore.Intent
import com.example.mvipaging.domain.CheeseStore.State

class CheeseExecutor(
    private val cheeseSource: CheeseSource
) : SuspendExecutor<Intent, Unit, State, Result, Nothing>() {
    override suspend fun executeIntent(intent: Intent, getState: () -> State) {
        when (intent) {
            is Intent.LoadMore -> loadMore(intent.index)
        }
    }

    override suspend fun executeAction(action: Unit, getState: () -> State) {
        dispatch(Result.NewItems(items = cheeseSource.get(0)))
    }

    private suspend fun loadMore(index: Int) {
        dispatch(Result.NewItems(items = cheeseSource.get(index)))
    }
}
