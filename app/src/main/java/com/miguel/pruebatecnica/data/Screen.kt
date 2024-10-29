package com.miguel.pruebatecnica.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Imagen URL", Icons.Filled.Home)
    object Profile : Screen("profile", "Pokemones", Icons.Filled.Person)
    object Settings : Screen("settings", "Configuración", Icons.Filled.Settings)
}