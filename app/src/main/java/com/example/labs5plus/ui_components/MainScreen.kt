package com.example.labs5plus.ui_components

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.labs5plus.di.MainViewModel
import com.example.labs5plus.utils.DrawerEvents
import com.example.labs5plus.utils.ListItem
import kotlinx.coroutines.launch


@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel(), onClick: (ListItem) -> Unit) {
  val topBarTitle = rememberSaveable { mutableStateOf("1 сезон") }
  val drawerState = rememberDrawerState(DrawerValue.Closed)
  val scope = rememberCoroutineScope()
  val selectedIndex = rememberSaveable { mutableIntStateOf(0) }
  val mainList = mainViewModel.mainList
  mainViewModel.getAllItemsByCategory(topBarTitle.value)
  if (topBarTitle.value == "Избранные") {
    mainViewModel.getFavorites()
  } else {
    mainViewModel.getAllItemsByCategory(topBarTitle.value)
  }

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      ModalDrawerSheet {
        DrawerMenu(
          onEvent = { event ->
            when (event) {
              is DrawerEvents.OnItemClick -> {
                topBarTitle.value = event.title
                mainViewModel.getAllItemsByCategory(event.title)
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
      Scaffold(
        topBar = {
          MainTopBar(title = topBarTitle.value, drawerState = drawerState){
            topBarTitle.value = "Избранные"
            mainViewModel.getFavorites()
          }
        }
      ) { innerPadding ->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
          if (mainList.value.isNotEmpty()) {
            items(mainList.value) { item ->
              MainListItem(item = item) { listItem ->
                onClick(listItem)
              }
            }
          }
        }
      }
    }
  )
}