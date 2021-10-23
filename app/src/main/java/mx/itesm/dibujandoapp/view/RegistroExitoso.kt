package mx.itesm.dibujandoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandoapp.databinding.RegistroExitosoFragment2Binding

/**
 *
 * Autor:
 * Alejandro Enriquez Coronado
 *
 * Última modificación:
 * 6 de octubre de 2021
 *
 * Descripción:
 * RegistroExitoso es el componente View de registro_exitoso_fragment2,
 * aqui se revisa cuando se seleccione el botón de entendido para
 * regresar a login_fragment
 *
 * */

class RegistroExitoso : Fragment() {

    private lateinit var binding: RegistroExitosoFragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegistroExitosoFragment2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Se revisan los eventos...
        configurarEvento()
    }

    private fun configurarEvento() {
        binding.btnEntendido.setOnClickListener {
            findNavController().navigate(RegistroExitosoDirections.actionRegistroExitosoToLogin())
        }
    }
}