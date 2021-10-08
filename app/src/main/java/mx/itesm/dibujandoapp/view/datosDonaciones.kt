package mx.itesm.dibujandoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandoapp.R
import mx.itesm.dibujandoapp.databinding.FragmentDatosDonacionesBinding

/**
 *
 * Autor:
 * Luis Ignacio Ferro Salinas
 * Última modificación:
 * 7 de octubre de 2021
 *
 * */

class datosDonaciones : Fragment() {

    private lateinit var binding: FragmentDatosDonacionesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatosDonacionesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        respondToTaps()
    }

    private fun respondToTaps() {
        binding.donarDatosDonacionesBtn.setOnClickListener {
            if (binding.nombreEditText.text.isEmpty() or binding.fechaEditTextDate.text.isEmpty() or binding.generoEditText.text.isEmpty() or binding.correoEditTextEmail.text.isEmpty() or binding.telefonoEditTextPhone.text.isEmpty() or binding.municipioEditText.text.isEmpty()) {
                Toast.makeText(getActivity(), "Por favor llene todos los campos",
                    Toast.LENGTH_LONG).show();
            } else {
                val myAction = datosDonacionesDirections.actionDatosDonacionesToBlankFragment()
                findNavController().navigate(myAction)
            }
        }
        // I need to check the state of the switch to know whether or not I have to annonunce the message.
        binding.deducibleSwitch.setOnClickListener {
            if (binding.deducibleSwitch.isChecked()) {
                Toast.makeText(getActivity(), "Por favor remita sus datos fiscales al correo entreamigos@dibujando.org.mx adjuntando el comprobante.",
                    Toast.LENGTH_LONG).show();
            }
        }
    }
}