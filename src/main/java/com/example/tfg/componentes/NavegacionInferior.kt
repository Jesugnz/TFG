package com.example.tfg.componentes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tfg.modelos.ItemsNav.*
import com.example.tfg.navegacion.rutaActual

@Composable
fun NavegacionInferior(navController: NavHostController) {
    //Esta función contiene la disposición de la barra de tareas
    val menu_items = listOf(
        Inicio,
        Dispositivos,
        Novedades,
        Cuenta
    )
    BottomAppBar(modifier = Modifier.fillMaxWidth().height(55.dp), containerColor = Color.White){
        NavigationBar(modifier = Modifier
            .fillMaxSize(), containerColor = Color.White){
            menu_items.forEach { item ->
                val selected = rutaActual(navController = navController) == item.ruta
                NavigationBarItem(
                    selected = selected,
                    onClick = { navController.navigate(item.ruta) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icono),
                            contentDescription = item.titulo
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        indicatorColor= Color.White
                    )
                )
            }
        }
    }
}