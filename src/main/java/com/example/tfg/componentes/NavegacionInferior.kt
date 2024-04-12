package com.example.tfg.componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
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
    val menu_items = listOf(
        Inicio,
        Dispositivos,
        Novedades,
        Cuenta
    )
    BottomAppBar {
        NavigationBar(containerColor = Color.White){
            menu_items.forEach { item ->
                val selected = rutaActual(navController = navController) == item.ruta
                NavigationBarItem(
                    selected = selected,
                    onClick = { navController.navigate(item.ruta) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icono),
                            contentDescription = item.titulo,
                            modifier = Modifier.padding(2.dp)
                        )
                    },
                    label = { Text(text = item.titulo) }
                )
            }
        }
    }
}