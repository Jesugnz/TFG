package com.example.tfg.clases

sealed class Estado_Diario {
    class Exito(val datos: Grafica_D) : Estado_Diario()
    class Fallo(val mensaje: String) : Estado_Diario()
    object Cargando : Estado_Diario()
    object Vacio : Estado_Diario()
}