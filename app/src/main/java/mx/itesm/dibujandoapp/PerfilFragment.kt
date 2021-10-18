package mx.itesm.dibujandoapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import mx.itesm.dibujandoapp.databinding.PerfilFragmentBinding

class PerfilFragment : Fragment() {

    companion object {
        fun newInstance() = PerfilFragment()
    }

    private lateinit var viewModel: PerfilVM
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: PerfilFragmentBinding
    private val usuario = mAuth.currentUser

    override fun onStart() {
        super.onStart()
        if (usuario != null) {
            println("Bienvenido NUEVAMENTE: ${usuario.displayName}")
            println("Correo: ${usuario.email}")
        } else {
            // Hacer Login
            val accion = PerfilFragmentDirections.actionPerfilFragmentToLogin()
            findNavController().navigate(accion)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PerfilFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (usuario != null) {
            binding.tvUsuarioActual.text = usuario.displayName
            binding.tvCorreoActual.text = usuario.email
        }

        configurarEventos()
    }

    private fun configurarEventos() {
        binding.btnLogOut.setOnClickListener {
            AuthUI.getInstance().signOut(requireContext())
            print("Log Out Exitoso")

            // Regresar a la pantalla principal
            val accion = PerfilFragmentDirections.actionPerfilFragmentToInformacionFragment()
            findNavController().navigate(accion)
        }
    }
}