package com.example.mvipaging.presentation

import com.arkivanov.mvikotlin.core.view.MviView
import com.example.mvipaging.domain.CheeseStore.Intent
import com.example.mvipaging.domain.CheeseStore.State

interface CheeseView : MviView<State, Intent>
