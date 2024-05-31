package com.example.tfg.modelos

import com.example.tfg.R
import com.example.tfg.navegacion.PantallaNav
//Estos son los objetos que se van a mostrar en la barra de navegación, indicando su imagen, nombre y ruta
sealed class ItemsNav(
    val icono: Int,
    val titulo: String,
    val ruta : String) {
    object Inicio: ItemsNav(R.drawable.home, "Inicio", PantallaNav.PantallaInicio.name)
    object Dispositivos: ItemsNav(R.drawable.dispositivos, "Dispositivos", PantallaNav.PantallaDispositivos.name)
    object Novedades: ItemsNav(R.drawable.novedades, "Novedades", PantallaNav.PantallaNovedades.name)
    object Cuenta: ItemsNav(R.drawable.cuenta, "Cuenta", PantallaNav.PantallaCuenta.name)
}