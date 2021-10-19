package mx.itesm.dibujandoapp.donaciones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandoapp.R
import mx.itesm.dibujandoapp.databinding.FragmentDonacionesBinding

/**
 *
 * Autor:
 * Luis Ignacio Ferro Salinas
 * Última modificación:
 * 14 de octubre de 2021
 *
 * */

class FragmentDonaciones : Fragment() {


    // This is the reference to the view.
    private lateinit var binding: FragmentDonacionesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // The binding contains the view that this onCreateView function returns.
        binding = FragmentDonacionesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentFinal.visibility = View.GONE
        respondToTaps()
    }

    private fun respondToTaps() {

        binding.campaignImageViewBtn1.setOnClickListener {
            val actionForCampaign1 = FragmentDonacionesDirections
                .actionFragmentDonacionesToDatosCausa("Juntos Otra Vez",
                    "Gracias a tus aportaciones seguimos juntos con el objetivo " +
                            "de mantener espacios seguros para niñas, niños y adolescentes de A " +
                            "Favor del Niño I.A.P. y CAYAM A.C. #VolverSeguros")
            findNavController().navigate(actionForCampaign1)
        }

        binding.campaignImageViewBtn2.setOnClickListener {
            val actionForCampaign1 = FragmentDonacionesDirections
                .actionFragmentDonacionesToDatosCausa("Entre amigos",
                    "¡Estamos buscando a 500 Entre Amigos! ¡Súmate y forma " +
                            "parte de una comunidad de personas que está contribuyendo a " +
                            "mejorar la vida de miles de niñas y niños!")
            findNavController().navigate(actionForCampaign1)
        }

        binding.payPalDonateOnceBtn.setOnClickListener {
            if (binding.montoPaypalEditTextDecimal.text.isEmpty()) {
                Toast.makeText(getActivity(), "Por favor llene el campo " +
                        "del Monto antes de continuar.",
                    Toast.LENGTH_LONG).show()
            } else if (binding.montoPaypalEditTextDecimal.text.toString().toFloat() >= 9999999.99) {
                binding.montoPaypalEditTextDecimal.setText("")
                Toast.makeText(
                    getActivity(), "Por favor introduzca un monto" +
                            " menor a 9999999.99",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val actionWithAmount = FragmentDonacionesDirections
                    .actionFragmentDonacionesToDatosDonaciones(binding.montoPaypalEditTextDecimal
                        .text
                        .toString()
                        .toFloat())
                findNavController().navigate(actionWithAmount)
            }
        }
    }
}