package com.example.labs5plus.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.labs5plus.utils.ListItem
@Dao
interface Dao {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)

    @Delete
    suspend fun deleteItem(item: ListItem)

    @Query("select * from main where category like :cat")
    suspend fun getAllItemsByCategory(cat:String): List<ListItem>
}
