package mx.itesm.dibujandoapp.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import mx.itesm.dibujandoapp.R
import mx.itesm.dibujandoapp.databinding.LoginFragmentBinding
import mx.itesm.dibujandoapp.viewmodel.LoginVM

class Login : Fragment() {

    var destiny: Int = -1

    private val CODIGO_SIGNIN: Int = 500

    private lateinit var viewModel: LoginVM

    private lateinit var binding: LoginFragmentBinding
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= LoginFragmentBinding.inflate(layoutInflater)
        val vista= binding.root
        return vista
    }

    private fun configurarEvento() {
        binding.btnRegistrar2.setOnClickListener {
            autenticar() // boton de arriba
            // Lanzar actividad perfil
            // Validar que perfil esta creado en la base de datos.
            destiny = 0
        }
        binding.btnRegistar.setOnClickListener {
            autenticar()
            destiny = 1
        }
    }

    override fun onStart() {
        super.onStart()
        val usuario = mAuth.currentUser
        if (usuario != null) {
            // Lanzar actividad perfil
            val accion = LoginDirections.actionLogin2ToPerfilFragment()
            findNavController().navigate(accion)
        } else {
            println("Hacer Login...")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODIGO_SIGNIN) {
            when (resultCode) {
                RESULT_OK -> {
                    val usuario = FirebaseAuth.getInstance().currentUser
                    FirebaseAuth.getInstance().currentUser
                    println("Bienvenido: ${usuario?.displayName}")
                    println("Correo: ${usuario?.email}")
                    println("Token: ${usuario?.uid}")
                    if (destiny == 0) {
                        val accion = LoginDirections.actionLogin2ToPerfilFragment()
                        findNavController().navigate(accion)
                    } else if (destiny == 1) {
                        val toFillInfo = LoginDirections.actionLogin2ToPantallaRegistro(FirebaseAuth.getInstance().currentUser?.email.toString())
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Eventos
        configurarEvento()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginVM::class.java)
    }
}