package mx.itesm.dibujandoapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandoapp.R
import mx.itesm.dibujandoapp.databinding.LoginFragmentBinding
import mx.itesm.dibujandoapp.databinding.PantallaRegistroFragmentBinding
import mx.itesm.dibujandoapp.donaciones.DatosDonacionesArgs
import mx.itesm.dibujandoapp.donaciones.DatosDonacionesDirections
import mx.itesm.dibujandoapp.viewmodel.PantallaRegistroVM
import mx.itesm.dibujandoapp.viewmodel.Usuario

/**
 * Autores:
 * Luis Ignacio Ferro Salinas
 * Última modificación:
 * 19 de octubre de 2021
 */

class PantallaRegistro : Fragment() {

    private val viewModel: PantallaRegistroVM by viewModels()
    private val args: PantallaRegistroArgs by navArgs()
    private lateinit var binding: PantallaRegistroFragmentBinding
    private lateinit var baseDatos: FirebaseDatabase
    //private val baseDatos = Firebase.database
    private val mAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PantallaRegistroFragmentBinding.inflate(layoutInflater)
        val vista = binding.root
        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Eventos
        baseDatos = Firebase.database
        binding.CorreoText.setText(args.correoGoogle)
        configurarEventos()
        subscribeToPhoneNumberChange()
        subscribeToDateChange()
        subscribeToGenderChange()
    }

    private fun configurarEventos() {

        binding.btnRegistar2.setOnClickListener {
            if (binding.nombreText.text.isEmpty() or
                binding.FechaText.text.isEmpty() or
                binding.generoEditTextR.text.isEmpty() or
                binding.CorreoText.text.isEmpty() or
                binding.telefonoEditTextPhoneR.text.isEmpty() or
                binding.municipioEditTextR.text.isEmpty()) {
                Toast.makeText(getActivity(), "Por favor llene todos los campos de manera " +
                        "correcta.",
                    Toast.LENGTH_LONG).show()
            } else {
                val usuarioGoogle = mAuth.currentUser

                val id = usuarioGoogle?.uid.toString()
                val nombre = binding.nombreText.text.toString()
                val fecha = binding.FechaText.text.toString()
                val correo = binding.CorreoText.text.toString()
                val genero = binding.generoEditTextR.text.toString()
                val telefono = binding.telefonoEditTextPhoneR.text.toString()
                val municipio = binding.municipioEditTextR.text.toString()
                val usuario = Usuario(id, nombre, fecha, correo, genero, telefono, municipio)
                val referencia = baseDatos.getReference("Usuarios/$id")
                referencia.setValue(usuario)

                val myAction = PantallaRegistroDirections.actionPantallaRegistroToRegistroExitoso()
                findNavController().navigate(myAction)
            }
        }
    }

    private fun subscribeToPhoneNumberChange() {
        binding.telefonoEditTextPhoneR.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the phone number.
            } else {
                if (!viewModel.acceptablePhoneNumber(binding
                        .telefonoEditTextPhoneR
                        .text
                        .toString())) {
                    binding.telefonoEditTextPhoneR.setText("")
                    Toast.makeText(getActivity(), "Por favor escriba un " +
                            "número de teléfono con lada",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun subscribeToDateChange() {
        binding.FechaText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the date.
            } else {
                if (!viewModel.acceptableDate(binding.FechaText.text.toString())) {
                    binding.FechaText.setText("")
                    Toast.makeText(getActivity(), "Por favor escriba una fecha valida",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun subscribeToGenderChange() {
        binding.generoEditTextR.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the gender.
            } else {
                if (!viewModel.acceptableGender(binding.generoEditTextR.text.toString())) {
                    binding.generoEditTextR.setText("")
                    Toast.makeText(getActivity(), "Por favor escriba M para masculino" +
                            " o F para femenino",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}