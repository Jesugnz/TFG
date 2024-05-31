package com.example.tfg.modelos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.clases.Estado_Diario
import com.example.tfg.clases.Grafica_D
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class Datos_Grafica_D : ViewModel(){
    var state : MutableState<Estado_Diario> = mutableStateOf(Estado_Diario.Cargando)
    val estado = mutableStateOf(Grafica_D())
    init {
        getDatos_mes()
    }
    private fun getDatos_mes(){
        viewModelScope.launch {
            state.value = Estado_Diario.Cargando
            estado.value = getGraficaDiariaFirestore()
            state.value = Estado_Diario.Exito(estado.value)
        }
    }
}
suspend fun getGraficaDiariaFirestore(): Grafica_D {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currente = auth.currentUser
    var datos_dia = mutableStateOf(Grafica_D())

    try{
        if (currente != null) {
            db.collection("Grafica_Diaria").whereEqualTo("id_usuario", currente.uid).get().await().map {
                val resultado = it.toObject(Grafica_D::class.java)
                datos_dia.value = resultado
                Log.d("grafica_dia", resultado.toString())
            }
        }
    }catch (e: FirebaseFirestoreException){
        Log.d("Error", "getGraficaDiariaFirestore: $e")
    }
    return datos_dia.value
}