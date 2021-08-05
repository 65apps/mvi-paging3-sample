package com.example.mvipaging.presentation

import com.example.mvipaging.data.Cheese

sealed class CheeseListItem(val name: String) {
    data class Item(val cheese: Cheese) : CheeseListItem(cheese.name)
    data class Separator(private val letter: Char) : CheeseListItem(
        letter.uppercaseChar()
            .toString()
    )
}
