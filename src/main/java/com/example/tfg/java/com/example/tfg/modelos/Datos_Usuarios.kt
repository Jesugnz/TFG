package com.example.tfg.modelos

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.clases.Usuarios
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Datos_Usuarios : ViewModel(){
    val estado = mutableStateOf(Usuarios())
    init {
        getDatos()
    }
    private fun getDatos(){
        viewModelScope.launch {
            estado.value = getUsuariosFirestore()
        }
    }
}
suspend fun getUsuariosFirestore():Usuarios{
    val auth = FirebaseAuth.getInstance()
    val currente = auth.currentUser
    val db = FirebaseFirestore.getInstance()
    var usuarios = mutableStateOf(Usuarios())
    if(currente != null){
        try{
            db.collection("Usuarios").whereEqualTo("id_usuario", currente.uid).get().await().map {
                val resultado = it.toObject(Usuarios::class.java)
                usuarios.value = resultado
                Log.d("Usuario", resultado.toString())
            }
        }catch (e: FirebaseFirestoreException){
            Log.d("Error", "getUsuariosFirestore: $e")
        }
    }
    return usuarios.value
}