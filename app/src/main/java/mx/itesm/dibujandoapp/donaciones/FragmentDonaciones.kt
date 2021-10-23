package mx.itesm.dibujandoapp.donaciones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandoapp.databinding.FragmentDonacionesBinding

/**
 *
 * Autor:
 * Luis Ignacio Ferro Salinas
 *
 * Última modificación:
 * 14 de octubre de 2021
 *
 * Descripción:
 * FragmentDonaciones es el componente View de fragment_donaciones,
 * aqui se revisa si el usuario selecciona algun botón para donaciones
 * y se revisa si el monto ingresado es válido
 *
 * */

class FragmentDonaciones : Fragment() {

    private lateinit var binding: FragmentDonacionesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDonacionesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentFinal.visibility = View.GONE

        // Se revisan los eventos...
        respondToTaps()
    }

    private fun respondToTaps() {

        // Se revisan interacciones con botones y campos de monto
        binding.campaignImageViewBtn1.setOnClickListener {
            val actionForCampaign1 = FragmentDonacionesDirections
                .actionFragmentDonacionesToDatosCausa("Juntos Otra Vez",
                    "Gracias a tus aportaciones seguimos juntos con el objetivo " +
                            "de mantener espacios seguros para niñas, niños y adolescentes de A " +
                            "Favor del Niño I.A.P. y CAYAM A.C. #VolverSeguros")
            findNavController().navigate(actionForCampaign1)
        }

        binding.campaignImageViewBtn2.setOnClickListener {
            val actionForCampaign2 = FragmentDonacionesDirections
                .actionFragmentDonacionesToDatosCausa("Entre amigos",
                    "¡Estamos buscando a 500 Entre Amigos! ¡Súmate y forma " +
                            "parte de una comunidad de personas que está contribuyendo a " +
                            "mejorar la vida de miles de niñas y niños!")
            findNavController().navigate(actionForCampaign2)
        }

        binding.payPalDonateOnceBtn.setOnClickListener {
            when {
                binding.montoPaypalEditTextDecimal.text.isEmpty() -> {
                    Toast.makeText(
                        activity, "Por favor llene el campo " +
                                "del Monto antes de continuar.",
                        Toast.LENGTH_LONG).show()
                }
                binding.montoPaypalEditTextDecimal.text.toString().toFloat() >= 9999999.99 -> {
                    binding.montoPaypalEditTextDecimal.setText("")
                    Toast.makeText(
                        activity, "Por favor introduzca un monto" +
                                " menor a 9999999.99",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val actionWithAmount = FragmentDonacionesDirections
                        .actionFragmentDonacionesToDatosDonaciones(binding
                            .montoPaypalEditTextDecimal
                            .text
                            .toString()
                            .toFloat())
                    findNavController().navigate(actionWithAmount)
                }
            }
        }
    }
}