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
 * 7 de octubre de 2021
 *
 * */

class FragmentDonaciones : Fragment() {


    // This is the reference to the view.
    private lateinit var binding: FragmentDonacionesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // The binding contains the view that this onCreateView function returns.
        binding = FragmentDonacionesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        respondToTaps()
    }

    private fun respondToTaps() {
        TODO("oh boi")
        /*
        val myAction = FragmentDonacionesDirections.actionScrollingFragmentToDatosDonaciones()
        binding.campaignImageViewBtn1.setOnClickListener {
            findNavController().navigate(myAction)
        }
        binding.campaignImageViewBtn2.setOnClickListener {
            findNavController().navigate(myAction)
        }
        binding.payPalDonateOnceBtn.setOnClickListener {
            findNavController().navigate(myAction)

        }
        binding.payPalDonateMonthlyBtn.setOnClickListener {
            findNavController().navigate(myAction)

        }
        binding.creditCardDonateBtn.setOnClickListener {
            if (binding.montoCreditCardEditTextDecimal.text.isEmpty()) {
                Toast.makeText(getActivity(), "Por favor introduzca un monto",
                    Toast.LENGTH_LONG).show();
            } else {
                findNavController().navigate(myAction)
            }
        }
        binding.payPalAddBtn.setOnClickListener {
            binding.montoPaypalEditTextDecimal.setText("MORE")
        }
        binding.payPalSubstractBtn.setOnClickListener {
            binding.montoPaypalEditTextDecimal.setText("LESS")
        }

         */
    }
}