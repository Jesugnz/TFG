package com.example.tfg.modelos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.clases.Estado_Semanal
import com.example.tfg.clases.Grafica_S
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Datos_Grafica_S : ViewModel(){
    var state : MutableState<Estado_Semanal> = mutableStateOf(Estado_Semanal.Cargando)
    val estado = mutableStateOf(Grafica_S())
    init {
        getDatos_mes()
    }
    private fun getDatos_mes(){
        viewModelScope.launch {
            state.value = Estado_Semanal.Cargando
            estado.value = getGraficaSemanalFirestore()
            state.value = Estado_Semanal.Exito(estado.value)
        }
    }
}
suspend fun getGraficaSemanalFirestore(): Grafica_S {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currente = auth.currentUser
    var datos_semana = mutableStateOf(Grafica_S())

    try{
        if (currente != null) {
            db.collection("Grafica_Semanal").whereEqualTo("id_usuario", currente.uid).get().await().map {
                val resultado = it.toObject(Grafica_S::class.java)
                datos_semana.value = resultado
                Log.d("grafica_semana", resultado.toString())
            }
        }
    }catch (e: FirebaseFirestoreException){
        Log.d("Error", "getGraficaSemanalFirestore: $e")
    }
    return datos_semana.value
}