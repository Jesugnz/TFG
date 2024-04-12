package com.example.tfg.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun rutaActual(navController: NavHostController): String? =
    navController.currentBackStackEntryAsState().value?.destination?.route