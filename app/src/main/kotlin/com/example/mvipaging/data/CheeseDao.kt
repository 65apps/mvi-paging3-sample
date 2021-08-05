package com.example.mvipaging.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CheeseDao {
    @Query("SELECT * FROM Cheese ORDER BY name COLLATE NOCASE ASC")
    fun entities(): PagingSource<Int, Cheese>

    @Insert
    fun insert(entities: List<Cheese>)
}
