package com.example.tfg.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class InicioViewModel : ViewModel() {
    //Esta clase contiene la lógica necesaria para poder iniciar sesión en la app
    private val auth: FirebaseAuth = Firebase.auth
    private val loading = MutableLiveData(false)

    fun Registro_con_email_contraseña(email: String, contraseña: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, contraseña)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Log.d("DomoEarth", "Logueado")
                            home
                        }
                        else{
                            Log.d("DomoEarth", "${task.result}")
                        }
                }
            } catch (ex: Exception) {
                Log.d("DomoEarth", "${ex.message}")
            }
        }
}