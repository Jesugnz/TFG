package com.example.tfg.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tfg.pantallas.PantallaAyuda
import com.example.tfg.pantallas.PantallaCuenta
import com.example.tfg.pantallas.PantallaDispositivo
import com.example.tfg.pantallas.PantallaInicio
import com.example.tfg.pantallas.PantallaNovedades
import com.example.tfg.pantallas.PantallaRegistro

@Composable
fun NavegacionApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PantallaNav.PantallaRegistro.name){
        composable(route = PantallaNav.PantallaRegistro.name){
            PantallaRegistro(navController)
        }
        composable(route = PantallaNav.PantallaInicio.name){
            PantallaInicio(navController)
        }
        composable(route = PantallaNav.PantallaDispositivos.name){
            PantallaDispositivo(navController)
        }
        composable(route = PantallaNav.PantallaNovedades.name){
            PantallaNovedades(navController)
        }
        composable(route = PantallaNav.PantallaCuenta.name){
            PantallaCuenta(navController)
        }
        composable(route = PantallaNav.PantallaAyuda.name){
            PantallaAyuda(navController)
        }
    }
}