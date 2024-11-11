package com.example.labs5plus.ui_components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.labs5plus.utils.DrawerEvents
import com.example.labs5plus.utils.IdArrayList
import com.example.labs5plus.utils.ListItem
import kotlinx.coroutines.launch

@Composable
fun MainScreen(context: Context, onClick : (ListItem) -> Unit){
    val topBarTitle = rememberSaveable{ mutableStateOf("1 сезон") }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val mainList = rememberSaveable { mutableStateOf(
        getListItemsByIndex(0, context)
    ) }
    val selectedIndex = remember { mutableIntStateOf(0) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerMenu(
                    onEvent = { event ->
                        when (event) {
                            is DrawerEvents.OnItemClick -> {
                                topBarTitle.value = event.title
                                mainList.value = getListItemsByIndex(event.index, context)
                                selectedIndex.intValue = event.index // Установить выбранный индекс
                            }
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    selectedIndex = selectedIndex.intValue // Передаем selectedIndex
                )
            }
        },
        content = {
            Scaffold (
                topBar = {
                    MainTopBar(title = topBarTitle.value, drawerState = drawerState/*, event = eventt*/)
                }
            ) { innerPadding ->
                LazyColumn(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                    items(mainList.value) { item ->
                        MainListItem(item = item) {
                            listItem -> onClick(listItem)
                        }
                    }
                }
            }
        }
    )
}

fun getListItemsByIndex(index: Int, context: Context) : List<ListItem>{
    val list = ArrayList<ListItem>()
    val arrayList = context.resources.getStringArray(IdArrayList.listId[index])

    arrayList.forEach { item ->
        val itemArray = item.split("|")
        list.add(
            ListItem(
                itemArray[0],
                itemArray[1],
                itemArray[2]
            )
        )
    }
    return list
}