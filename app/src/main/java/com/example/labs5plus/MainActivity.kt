package com.example.labs5plus

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labs5plus.ui.theme.Labs5plusTheme
import com.example.labs5plus.ui_components.DrawerMenu
import com.example.labs5plus.ui_components.InfoScreen
import com.example.labs5plus.ui_components.MainListItem
import com.example.labs5plus.ui_components.MainScreen
import com.example.labs5plus.ui_components.MainTopBar
import com.example.labs5plus.utils.DrawerEvents
import com.example.labs5plus.utils.IdArrayList
import com.example.labs5plus.utils.ItemSaver
import com.example.labs5plus.utils.ListItem
import com.example.labs5plus.utils.Routes
import kotlinx.coroutines.launch

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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Labs5plusTheme {
        Greeting("Android")
    }
}