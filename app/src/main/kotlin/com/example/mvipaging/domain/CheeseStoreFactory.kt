package com.example.mvipaging.domain

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.example.mvipaging.data.CheeseDao
import com.example.mvipaging.data.CheeseDatabaseSource
import com.example.mvipaging.domain.CheeseStore.Intent
import com.example.mvipaging.domain.CheeseStore.State

class CheeseStoreFactory(
    private val storeFactory: StoreFactory,
    private val dao: CheeseDao
) {
    fun create(): CheeseStore {
        val storeDelegate = storeFactory.create(
            name = CheeseStore::class.simpleName,
            initialState = State(items = emptyList()),
            executorFactory = { CheeseExecutor(CheeseDatabaseSource(dao)) },
            bootstrapper = SimpleBootstrapper(Unit),
            reducer = CheeseReducer()
        )
        return object : CheeseStore, Store<Intent, State, Nothing> by storeDelegate {}
    }
}
