package mx.itesm.dibujandoapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandoapp.databinding.PantallaRegistroFragmentBinding
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
    private val mAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PantallaRegistroFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Eventos
        baseDatos = Firebase.database
        binding.CorreoText.setText(args.correoGoogle)
        configurarEventos()
        subscribeToPhoneNumberChange()
        subscribeToEmailChange()
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
                Toast.makeText(
                    activity, "Por favor llene todos los campos de manera " +
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
                    Toast.makeText(
                        activity, "Por favor escriba un " +
                            "número de teléfono con lada",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun subscribeToEmailChange() {
        binding.CorreoText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the email.
            } else {
                if (!viewModel.acceptableEmail(binding.CorreoText.text.toString())) {
                    binding.CorreoText.setText("")
                    Toast.makeText(
                        activity, "Por favor escriba un correo valido con @",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun subscribeToDateChange() {
        binding.FechaText.addTextChangedListener {
            if(binding.FechaText.text.toString().length == 2 ||
                binding.FechaText.text.toString().length == 5){
                binding.FechaText.setText(binding.FechaText.text.toString()+"/")
                binding.FechaText.setSelection(binding.FechaText.text.toString().length)
            }
        }

        binding.FechaText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the date.
            } else {
                if (!viewModel.acceptableDate(binding.FechaText.text.toString())) {
                    binding.FechaText.setText("")
                    Toast.makeText(
                        activity, "Por favor escriba una fecha valida",
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
                    Toast.makeText(
                        activity, "Por favor escriba M para masculino" +
                            " o F para femenino",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}