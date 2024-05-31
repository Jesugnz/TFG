package com.example.tfg.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
//Esta función indica lo que debe hacer la navegación para volver a la pantalla anterior
@Composable
fun rutaActual(navController: NavHostController): String? =
    navController.currentBackStackEntryAsState().value?.destination?.route