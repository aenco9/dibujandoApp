package mx.itesm.dibujandoapp.donaciones

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mx.itesm.dibujandoapp.R
import mx.itesm.dibujandoapp.databinding.FragmentDatosDonacionesBinding

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
        respondToTaps()
        subscribeToGenderChange()
        subscribeToEmailChange()
        subscribeToDateChange()
        //TODO("revisar la fecha, el numero de telefono y ya")
    }

    private fun subscribeToDateChange() {
        binding.fechaEditTextDate.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the date.
            } else {
                if (!viewModel.acceptableDate(binding.fechaEditTextDate.text.toString())) {
                    binding.fechaEditTextDate.setText("")
                    Toast.makeText(getActivity(), "Por favor escriba una fecha valida",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }    }

    private fun subscribeToEmailChange() {
        binding.correoEditTextEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // User is modifying the email.
            } else {
                if (!viewModel.acceptableEmail(binding.correoEditTextEmail.text.toString())) {
                    binding.correoEditTextEmail.setText("")
                    Toast.makeText(getActivity(), "Por favor escriba un correo valido con @",
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
                    Toast.makeText(getActivity(), "Por favor escriba M para masculino" +
                            " o F para femenino",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun respondToTaps() {
        binding.donarDatosDonacionesBtn.setOnClickListener {
            if (binding.nombreEditText.text.isEmpty() or
                binding.fechaEditTextDate.text.isEmpty() or
                binding.generoEditText.text.isEmpty() or
                binding.correoEditTextEmail.text.isEmpty() or
                binding.telefonoEditTextPhone.text.isEmpty() or
                binding.municipioEditText.text.isEmpty()) {
                Toast.makeText(getActivity(), "Por favor llene todos los campos de manera " +
                        "correcta.",
                    Toast.LENGTH_LONG).show()
            } else {
                val myAction = DatosDonacionesDirections
                    .actionDatosDonacionesToPaypalDonation(args.monto)
                findNavController().navigate(myAction)
            }
        }
        // I need to check the state of the switch to know whether or not I have to
        // nnonunce the message.
        binding.deducibleSwitch.setOnClickListener {
            if (binding.deducibleSwitch.isChecked()) {
                Toast.makeText(getActivity(), "Por favor remita sus datos fiscales al " +
                        "correo entreamigos@dibujando.org.mx adjuntando el comprobante.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}