package com.example.tfg.modelos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.clases.Dispositivos_Electrodomesticos
import com.example.tfg.clases.Estado_Electrodomesticos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Datos_Dispositivos_Elec : ViewModel(){
    var state : MutableState<Estado_Electrodomesticos> = mutableStateOf(Estado_Electrodomesticos.Cargando)
    val estado = mutableStateOf(Dispositivos_Electrodomesticos())
    init {
        getDatos_de()
    }
    private fun getDatos_de(){
        viewModelScope.launch {
            state.value = Estado_Electrodomesticos.Cargando
            estado.value = getDispositivos_Elec_Firestore()
            state.value = Estado_Electrodomesticos.Exito(estado.value)
        }
    }
}
suspend fun getDispositivos_Elec_Firestore(): Dispositivos_Electrodomesticos {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currente = auth.currentUser
    var dispositivos = Dispositivos_Electrodomesticos()

    try{
        if (currente != null) {
            db.collection("Dispositivos_Electrodomesticos").whereEqualTo("id_usuario", currente.uid).get().await().map {
                val resultado = it.toObject(Dispositivos_Electrodomesticos::class.java)
                dispositivos = resultado
            }
        }
    }catch (e: FirebaseFirestoreException){
        Log.d("Error", "getDispositivos_Elec_Firestore: $e")
    }
    return dispositivos
}