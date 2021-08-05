package com.example.mvipaging.domain

import com.example.mvipaging.presentation.CheeseListItem

interface CheeseSource {
    suspend fun get(index: Int): List<CheeseListItem?>
}
