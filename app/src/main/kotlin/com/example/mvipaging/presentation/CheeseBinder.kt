package com.example.mvipaging.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.mvipaging.data.CheeseDb
import com.example.mvipaging.domain.CheeseStore
import com.example.mvipaging.domain.CheeseStoreFactory

class CheeseBinder(
    application: Application
) : AndroidViewModel(application) {
    private val store: CheeseStore

    init {
        val dao = CheeseDb.get(application).cheeseDao()
        store = CheeseStoreFactory(DefaultStoreFactory, dao).create()
    }

    fun onViewCreated(view: CheeseView, lifecycle: Lifecycle) {
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states bindTo view
            view.events bindTo store
        }
    }
}
