package com.example.labs5plus.utils

import androidx.compose.runtime.saveable.listSaver

data class ListItem(val title: String, val imageName: String, val htmlName: String)

val ItemSaver = listSaver<ListItem, Any>(
    save = { listOf(it.title, it.imageName, it.htmlName)},
    restore = {ListItem(it[0] as String, it[1] as String, it[2] as String)}
)
