package com.example.tfg.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tfg.pantallas.PantallaAyuda
import com.example.tfg.pantallas.PantallaCuenta
import com.example.tfg.pantallas.PantallaDispositivo
import com.example.tfg.pantallas.PantallaDispositivoElec
import com.example.tfg.pantallas.PantallaDispositivoSeg
import com.example.tfg.pantallas.PantallaDispositivoTemp
import com.example.tfg.pantallas.PantallaGraficaDiaria
import com.example.tfg.pantallas.PantallaGraficaMensual
import com.example.tfg.pantallas.PantallaGraficaSemanal
import com.example.tfg.pantallas.PantallaInicio
import com.example.tfg.pantallas.PantallaNovedades
import com.example.tfg.pantallas.PantallaRegistro

@Composable
fun NavegacionApp(){
    //Recogemos las rutas de las diferentes pantallas y con un navCOntroller, indicamos como debe desplazarse
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
        composable(route = PantallaNav.PantallaGraficaDiaria.name){
            PantallaGraficaDiaria(navController)
        }
        composable(route = PantallaNav.PantallaGraficaSemanal.name){
            PantallaGraficaSemanal(navController)
        }
        composable(route = PantallaNav.PantallaGraficaMensual.name){
            PantallaGraficaMensual(navController)
        }
        composable(route = PantallaNav.PantallaDispositivoTemp.name + "/{id}"){backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            PantallaDispositivoTemp(navController, id = id)
        }
        composable(route = PantallaNav.PantallaDispositivoSeg.name + "/{id}"){backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            PantallaDispositivoSeg(navController, id = id)
        }
        composable(route = PantallaNav.PantallaDispositivoElec.name + "/{dato}"){backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            PantallaDispositivoElec(navController, id = id)

        }
    }
}