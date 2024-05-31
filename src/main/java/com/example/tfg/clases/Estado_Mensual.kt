package com.example.tfg.clases

sealed class Estado_Mensual {
    class Exito (val datos: Grafica_M) : Estado_Mensual()
    class Fallo(val mensaje: String) : Estado_Mensual()
    object Cargando : Estado_Mensual()
    object Vacio : Estado_Mensual()
}