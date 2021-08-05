package com.example.mvipaging.domain

import com.arkivanov.mvikotlin.core.store.Store
import com.example.mvipaging.domain.CheeseStore.Intent
import com.example.mvipaging.domain.CheeseStore.State
import com.example.mvipaging.presentation.CheeseListItem

interface CheeseStore : Store<Intent, State, Nothing> {
    sealed class Intent {
        class LoadMore(val index: Int) : Intent()
    }

    data class State(
        val items: List<CheeseListItem?>
    )
}
