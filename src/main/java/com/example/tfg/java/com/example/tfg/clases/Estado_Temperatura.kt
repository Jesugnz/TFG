package com.example.tfg.clases

sealed class Estado_Temperatura {
    class Exito(val datos: Dispositivos_Temperatura) : Estado_Temperatura()
    class Fallo(val mensaje: String) : Estado_Temperatura()
    object Cargando : Estado_Temperatura()
    object Vacio : Estado_Temperatura()
}