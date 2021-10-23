package mx.itesm.dibujandoapp.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandoapp.databinding.LoginFragmentBinding

/**
 *
 * Autores:
 * Alejandro Enriquez Coronado
 * Eric Alexis Castañeda Bravo
 * Joan Daniel Guerrero García
 * Luis Ignacio Ferro Salinas
 *
 * Última modificación:
 * 22 de octubre de 2021
 *
 * Descripción:
 * DatosDonaciones es el componente View de fragment_datos_donaciones,
 * aqui se verifican los datos que se ingresen del usuario y actualiza
 * los campos si el usuario ya esta registrado.
 *
 * */

class Login : Fragment() {

    var registrado: Boolean = false
    private var destiny: Int = -1
    private val CODIGO_SIGNIN: Int = 500
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: LoginFragmentBinding
    private lateinit var baseDatos: FirebaseDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseDatos = Firebase.database
        revisarFirebase()

        if (FirebaseAuth.getInstance().currentUser != null) {
            println("Si hay usuario")
            // Lanzar actividad perfil
            irAPerfil()
        } else {
            println("Hacer Login...")
        }

        // Se revisan los eventos...
        configurarEvento()
    }

    private fun configurarEvento() {
        binding.btnLogIn.setOnClickListener {
            // Destiny = 0 -> Apretó el botón de Log In
            destiny = 0
            autenticar()
        }
        binding.btnRegistar.setOnClickListener {
            // Destiny = 1 -> Apretó el botón de Registro
            destiny = 1
            autenticar()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Se revisa cuando se reciba algun código
        // proporcionado por Google por el método autenticar
        if (requestCode == CODIGO_SIGNIN) {
            when (resultCode) {
                RESULT_OK -> {
                    if (destiny == 0) {
                        println("HACER LOGIN")
                        val usuarioGoogle = FirebaseAuth.getInstance().currentUser
                        println("Bienvenido: ${usuarioGoogle?.displayName}")
                        println("Correo: ${usuarioGoogle?.email}")
                        println("Token: ${usuarioGoogle?.uid}")

                        irAPerfil()

                    } else if (destiny == 1) {
                        println("HACER REGISTRO")
                        val toFillInfo = LoginDirections.actionLoginToPantallaRegistro(FirebaseAuth
                                .getInstance()
                                .currentUser?.email.toString())
                        destiny = -1
                        findNavController().navigate(toFillInfo)
                    }
                }
                AppCompatActivity.RESULT_CANCELED -> {
                    println("Cancelado...")
                    val response =
                        IdpResponse.fromResultIntent(data)
                    println("Error:${response?.error?.localizedMessage}")
                }
                else -> {
                    val response =
                        IdpResponse.fromResultIntent(data)
                    println("Error:${response?.error?.errorCode}")
                }
            }
        }
    }

    private fun autenticar() {
        // Se ejecuta el complemento de Google para hacer Log In
        val providers =
            arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            CODIGO_SIGNIN
        )
    }

    private fun revisarFirebase() {
        // Aquí se ejecuta el método onDataChange para revisar si el usuario
        // ya esta en nuestra base de datos de FireBase

        val usuario = mAuth.currentUser
        val myReference = baseDatos.getReference("Usuarios/")
        myReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (usuario != null) {
                    if (snapshot.hasChild(usuario.uid)) {
                        // El usuario si está en la base de datos y ya está registrado
                        registrado = true
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun irAPerfil(){
        println(registrado)
        if(registrado){
            // Si ya esta registrado, ir al Perfil
            destiny = -1
            findNavController().navigate(LoginDirections.actionLoginToPerfilFragment())
        }
        else {
            Toast.makeText(
                activity, "No se ha registrado con" +
                        " esta cuenta de google, por favor regístrese.",
                Toast.LENGTH_SHORT).show()
        }
    }
}