package com.example.tfg.clases

sealed class Estado_Electrodomesticos {
    class Exito(val datos: Dispositivos_Electrodomesticos) : Estado_Electrodomesticos()
    class Fallo(val mensaje: String) : Estado_Electrodomesticos()
    object Cargando : Estado_Electrodomesticos()
    object Vacio : Estado_Electrodomesticos()
}