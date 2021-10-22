package mx.itesm.dibujandoapp.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
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
import mx.itesm.dibujandoapp.viewmodel.LoginVM


class Login : Fragment() {

    private var destiny: Int = -1
    var registrado: Boolean = false

    private val CODIGO_SIGNIN: Int = 500

    private lateinit var viewModel: LoginVM

    private lateinit var binding: LoginFragmentBinding
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
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
        //Eventos
        baseDatos = Firebase.database
        revisarFirebase()

        if (FirebaseAuth.getInstance().currentUser != null) {
            println("Si hay usuario")
            // Lanzar actividad perfil
            irALogin()
        } else {
            println("Hacer Login...")
        }

        configurarEvento()
    }

    private fun configurarEvento() {
        binding.btnLogIn.setOnClickListener {
            destiny = 0
            autenticar()
        }
        binding.btnRegistar.setOnClickListener {
            destiny = 1
            autenticar()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODIGO_SIGNIN) {
            when (resultCode) {
                RESULT_OK -> {
                    //revisarFirebase()

                    if (destiny == 0) {
                        println("HACER LOGIN")
                        val usuarioGoogle = FirebaseAuth.getInstance().currentUser
                        println("Bienvenido: ${usuarioGoogle?.displayName}")
                        println("Correo: ${usuarioGoogle?.email}")
                        println("Token: ${usuarioGoogle?.uid}")

                        irALogin()

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
        val usuario = mAuth.currentUser
        val myReference = baseDatos.getReference("Usuarios/")
        myReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (usuario != null) {
                    if (snapshot.hasChild(usuario.uid)) {
                        println("Moviendose a Perfil desde Login...")
                        registrado = true
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun irALogin(){
        println("Ir a login...")
        println(registrado)
        if(registrado){
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginVM::class.java)
    }
}