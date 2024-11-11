package com.example.labs5plus.ui_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, drawerState: DrawerState/*, event: DrawerEvents?*/){
    val coroutine = rememberCoroutineScope()
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                coroutine.launch { drawerState.open() }
            }) { Icon(imageVector = Icons.Default.Menu, contentDescription = "Меню")}
        },
        actions = {
            IconButton(onClick = {  }) {
                //if(title == R.array.drawer_list)
                if(title == "Любимки") Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = "Любимый раздел")
            }
        }
    )
}