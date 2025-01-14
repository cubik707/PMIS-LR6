package com.example.labs5plus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labs5plus.ui.theme.Labs5plusTheme
import com.example.labs5plus.ui_components.InfoScreen
import com.example.labs5plus.ui_components.MainScreen
import com.example.labs5plus.utils.ItemSaver
import com.example.labs5plus.utils.ListItem
import com.example.labs5plus.utils.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var item = rememberSaveable(stateSaver = ItemSaver)
            { mutableStateOf(ListItem("", "", "")) }
            Labs5plusTheme {
                NavHost(navController = navController,
                    startDestination = Routes.MAIN_SCREEN.route) {
                    composable (Routes.MAIN_SCREEN.route) {
                        MainScreen(this@MainActivity){ listItem ->
                            item.value = ListItem(listItem.title
                                , listItem.imageName, listItem.htmlName)
                            navController.navigate(Routes.INFO_SCREEN.route)
                        }
                    }
                    composable (Routes.INFO_SCREEN.route) {
                        InfoScreen(item = item.value)
                    }
                }

            }
        }
    }
}
