package com.example.labs5plus.utils

import androidx.compose.runtime.saveable.listSaver
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main")
data class ListItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val title: String,
    val imageName: String,
    val htmlName: String,
    val isfav: Boolean,
    val category:String
)


val ItemSaver = listSaver<ListItem, Any>(
    save = {
        listOf(it.id!!,it.title,it.imageName,it.htmlName,it.isfav,it.category)},
    restore = { ListItem(it[0] as Int?,it[1] as String, it[2] as String, it[3] as
            String,it[4] as Boolean,it[5] as String) }
)

