package com.miguel.pruebatecnica.ui.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miguel.pruebatecnica.data.Screen
import com.miguel.pruebatecnica.ui.theme.PruebaTecnicaTheme
import com.miguel.pruebatecnica.viewModel.PokemonViewModel
import com.miguel.pruebatecnica.ui.views.imageUrl.ImageUrlButton
import com.miguel.pruebatecnica.ui.views.pokemon.Pokemon

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaTecnicaTheme {
                val viewModel = PokemonViewModel()
                val pokemonesLista by viewModel.pokemonList.observeAsState()
                val navController = rememberNavController()
                var url = rememberSaveable {
                    mutableStateOf("")
                }
                var name = rememberSaveable {
                    mutableStateOf("")
                }
                if (pokemonesLista != null) {
                    Scaffold(bottomBar = {
                        BottomNavigationBar(
                            navController,
                            listOf(Screen.Home, Screen.Profile, Screen.Settings)
                        )
                    }) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = Screen.Home.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.Home.route) {
                                ImageUrlButton(url = url.value, name = name.value, onChangeUrl = {
                                    url.value = it
                                }, onChangeName = {
                                    name.value = it
                                })
                            }
                            composable(Screen.Profile.route) {
                                Pokemon(pokemonesLista!!.sortedWith(compareBy { it.id })) { id, url ->

                                }
                            }
                            composable(Screen.Settings.route) { }
                        }

                    }
                } else {
                    viewModel.getPokemons()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<Screen>
) {
    val currentRoute = currentRoute(navController)

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}