package com.example.tfg.modelos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.clases.Dispositivos_Temperatura
import com.example.tfg.clases.Estado_Temperatura
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Datos_Dispositivos_Temp : ViewModel(){
    var state : MutableState<Estado_Temperatura> = mutableStateOf(Estado_Temperatura.Cargando)
    val estado = mutableStateOf(Dispositivos_Temperatura())
    init {
        getDatos_dt()
    }
    private fun getDatos_dt(){
        viewModelScope.launch {
            state.value = Estado_Temperatura.Cargando
            estado.value = getDispositivos_Temp_Firestore()
            state.value = Estado_Temperatura.Exito(estado.value)
        }
    }
}
suspend fun getDispositivos_Temp_Firestore(): Dispositivos_Temperatura {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currente = auth.currentUser
    var dispositivos = mutableStateOf(Dispositivos_Temperatura())

    try{
        if (currente != null) {
            db.collection("Dispositivos_Temperatura").whereEqualTo("id_usuario", currente.uid).get().await().map {
                val resultado = it.toObject(Dispositivos_Temperatura::class.java)
                dispositivos.value = resultado
            }
        }
    }catch (e: FirebaseFirestoreException){
        Log.d("Error", "getDispositivos_Temp_Firestore: $e")
    }
    return dispositivos.value
}