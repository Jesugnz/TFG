package com.example.tfg.modelos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.clases.Estado_Mensual
import com.example.tfg.clases.Datos_M
import com.example.tfg.clases.Grafica_M
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Datos_Grafica_M : ViewModel(){
    var state : MutableState<Estado_Mensual> = mutableStateOf(Estado_Mensual.Cargando)
    val estado = mutableStateOf(Grafica_M())
    init {
        getDatos_mes()
    }
    private fun getDatos_mes(){
        viewModelScope.launch {
            state.value = Estado_Mensual.Cargando
            estado.value = getGraficaMensualFirestore()
            state.value = Estado_Mensual.Exito(estado.value)
        }
    }
}
suspend fun getGraficaMensualFirestore(): Grafica_M {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currente = auth.currentUser
    var datos_mes = mutableStateOf(Grafica_M())
    try{
        if (currente != null) {
            db.collection("Grafica_Mensual").whereEqualTo("id_usuario", currente.uid).get().await().map {
                val resultado = it.toObject(Grafica_M::class.java)
                datos_mes.value = resultado
                Log.d("grafica_mes", resultado.toString())
            }
        }
    }catch (e: FirebaseFirestoreException){
        Log.d("Error", "getGraficaMensualFirestore: $e")
    }
    return datos_mes.value
}