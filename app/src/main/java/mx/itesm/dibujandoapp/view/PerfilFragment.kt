package mx.itesm.dibujandoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import mx.itesm.dibujandoapp.databinding.PerfilFragmentBinding

/**
 *
 * Autores:
 * Joan Daniel Guerrero García
 * Luis Ignacio Ferro Salinas
 *
 * Última modificación:
 * 21 de octubre de 2021
 *
 * Descripción:
 * PerfilFragment es el componente View de perfil_fragment,
 * aqui se reciben y se muestran los datos del usuario registrado,
 * leyendo también la interacción con el botón de cerrar sesión.
 *
 * */

class PerfilFragment : Fragment() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val usuario = mAuth.currentUser
    private lateinit var binding: PerfilFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PerfilFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Se actualiza la información del layout si el usuario está registrado
        if (usuario != null) {
            binding.tvUsuarioActual.text = usuario.displayName
            binding.tvCorreoActual.text = usuario.email
        }

        // Se revisan los eventos...
        configurarEventos()
    }

    override fun onStart() {
        super.onStart()

        // Se revisa si el usuario está registrado,
        // si no es así, se regresa a informacion_fragment
        if (usuario != null) {
            val usuarioGoogle = FirebaseAuth.getInstance().currentUser
            println("Bienvenido NUEVAMENTE: ${usuarioGoogle?.displayName}")
            println("Correo: ${usuarioGoogle?.email}")
        } else {
            // Hacer Login
            val accion = PerfilFragmentDirections.actionPerfilFragmentToInformacionFragment()
            findNavController().navigate(accion)
        }
    }

    private fun configurarEventos() {
        // Se configura el botón de Log Out para cerrar sesión
        // y regresar a informacion_fragment
        binding.btnLogOut.setOnClickListener {
            AuthUI.getInstance().signOut(requireContext())
            print("Log Out Exitoso")
            Toast.makeText(
                activity, "Sesión cerrada exitosamente",
                Toast.LENGTH_SHORT).show()

            // Regresar a la pantalla principal
            val accion = PerfilFragmentDirections.actionPerfilFragmentToInformacionFragment()
            findNavController().navigate(accion)
        }
    }
}