package com.example.tfg.clases

sealed class Estado_Semanal {
    class Exito(val datos: Grafica_S) : Estado_Semanal()
    class Fallo(val mensaje: String) : Estado_Semanal()
    object Cargando : Estado_Semanal()
    object Vacio : Estado_Semanal()
}