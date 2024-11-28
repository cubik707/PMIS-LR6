package com.example.labs5plus.di

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.labs5plus.db.MainDb
import com.example.labs5plus.utils.ListItem
import kotlinx.coroutines.launch


@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDb: MainDb
):ViewModel() {
    val mainList = mutableStateOf(emptyList<ListItem>())
    fun getAllItemsByCategory(cat: String) =viewModelScope.launch {
        mainList.value = mainDb.dao.getAllItemsByCategory(cat)
    }
    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.insertItem(item)
    }
    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.deleteItem(item)
    }
}