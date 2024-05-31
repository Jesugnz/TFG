package com.example.tfg.clases

sealed class Estado_Seguridad {
    class Exito(val datos: Dispositivos_Seguridad) : Estado_Seguridad()
    class Fallo(val mensaje: String) : Estado_Seguridad()
    object Cargando : Estado_Seguridad()
    object Vacio : Estado_Seguridad()
}