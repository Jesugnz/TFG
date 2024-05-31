package com.example.tfg.modelos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.clases.Dispositivos_Seguridad
import com.example.tfg.clases.Estado_Seguridad
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Datos_Dispositivos_Seg : ViewModel(){
    var state : MutableState<Estado_Seguridad> = mutableStateOf(Estado_Seguridad.Cargando)
    val estado = mutableStateOf(Dispositivos_Seguridad())
    init {
        getDatos_ds()
    }
    private fun getDatos_ds(){
        viewModelScope.launch {
            state.value = Estado_Seguridad.Cargando
            estado.value = getDispositivos_Seg_Firestore()
            state.value = Estado_Seguridad.Exito(estado.value)
            estado.value = getDispositivos_Seg_Firestore()
        }
    }
}
suspend fun getDispositivos_Seg_Firestore(): Dispositivos_Seguridad {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currente = auth.currentUser
    var dispositivos = Dispositivos_Seguridad()

    try{
        if (currente != null) {
            db.collection("Dispositivos_Seguridad").whereEqualTo("id_usuario", currente.uid).get().await().map {
                val resultado = it.toObject(Dispositivos_Seguridad::class.java)
                dispositivos = resultado
            }
        }
    }catch (e: FirebaseFirestoreException){
        Log.d("Error", "getDispositivos_Seg_Firestore: $e")
    }
    return dispositivos
}