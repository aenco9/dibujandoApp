package mx.itesm.dibujandoapp.donaciones

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandoapp.databinding.FragmentDatosDonacionesBinding
import mx.itesm.dibujandoapp.viewmodel.Usuario

/**
 *
 * Autor:
 * Luis Ignacio Ferro Salinas
 * Última modificación:
 * 14 de octubre de 2021
 *
 * */

class DatosDonaciones : Fragment() {

    private lateinit var binding: FragmentDatosDonacionesBinding
    private val args: DatosDonacionesArgs by navArgs()
    private val viewModel: DatosDonacionesVM by viewModels()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var baseDatos: FirebaseDatabase
    private val usuario = mAuth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDatosDonacionesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseDatos = Firebase.database
        if(usuario != null)
        {
            // Si el usuario hizo login, esto implica que sus datos están en Firebase.
            val myReference = baseDatos.getReference("Usuarios/")
            myReference.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                val usuarioFirebase = snapshot.child(usuario.uid).getValue(Usuario::class.java)
                binding.nombreEditText.setText(usuario.displayName)
                binding.correoEditTextEmail.setText(usuario.email)
                binding.generoEditText.setText(usuarioFirebase?.genero)
                binding.telefonoEditTextPhone.setText(usuarioFirebase?.telefono)
                binding.municipioEditText.setText(usuarioFirebase?.municipio)
                binding.fechaEditText.setText(usuarioFirebase?.fecha)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
        respondToTaps()
        subscribeToGenderChange()
        subscribeToEmailChange()
        subscribeToDateChange()
        subscribeToPhoneNumberChange()
    }

    private fun subscribeToPhoneNumberChange() {
        binding.telefonoEditTextPhone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the phone number.
            } else {
                if (!viewModel.acceptablePhoneNumber(binding
                        .telefonoEditTextPhone
                        .text
                        .toString())) {
                    binding.telefonoEditTextPhone.setText("")
                    Toast.makeText(
                        activity, "Por favor escriba un " +
                            "número de teléfono con lada",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun subscribeToDateChange() {
        binding.fechaEditText.addTextChangedListener {
            if(binding.fechaEditText.text.toString().length == 2 ||
                binding.fechaEditText.text.toString().length == 5){
                binding.fechaEditText.setText(binding.fechaEditText.text.toString()+"/")
                binding.fechaEditText.setSelection(binding.fechaEditText.text.toString().length)
            }
        }

        binding.fechaEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the date.
            } else {
                if (!viewModel.acceptableDate(binding.fechaEditText.text.toString())) {
                    binding.fechaEditText.setText("")
                    Toast.makeText(
                        activity, "Por favor escriba una fecha valida",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun subscribeToEmailChange() {
        binding.correoEditTextEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the email.
            } else {
                if (!viewModel.acceptableEmail(binding.correoEditTextEmail.text.toString())) {
                    binding.correoEditTextEmail.setText("")
                    Toast.makeText(
                        activity, "Por favor escriba un correo valido con @",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun subscribeToGenderChange() {
        binding.generoEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the gender.
            } else {
                if (!viewModel.acceptableGender(binding.generoEditText.text.toString())) {
                    binding.generoEditText.setText("")
                    Toast.makeText(
                        activity, "Por favor escriba M para masculino" +
                            " o F para femenino",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun respondToTaps() {

        // I need to check the state of the swittch to know whether or not I have to
                // annonunce the message.

        binding.donarDatosDonacionesBtn.setOnClickListener {
                    if (binding.nombreEditText.text.isEmpty() or
                        binding.fechaEditText.text.isEmpty() or
                        binding.generoEditText.text.isEmpty() or
                        binding.correoEditTextEmail.text.isEmpty() or
                        binding.telefonoEditTextPhone.text.isEmpty() or
                        binding.municipioEditText.text.isEmpty()) {
                        Toast.makeText(
                            activity, "Por favor llene todos los campos de manera " +
                                "correcta.",
                            Toast.LENGTH_LONG).show()
                    } else {
                        val myAction = DatosDonacionesDirections
                            .actionDatosDonacionesToPaypalDonation(args.monto, args.titulo)
                        findNavController().navigate(myAction)
                    }
        }
        binding.deducibleSwitch.setOnClickListener {
            if (binding.deducibleSwitch.isChecked) {
                Toast.makeText(
                    activity, "Por favor remita sus datos fiscales al " +
                        "correo entreamigos@dibujando.org.mx adjuntando el comprobante.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}