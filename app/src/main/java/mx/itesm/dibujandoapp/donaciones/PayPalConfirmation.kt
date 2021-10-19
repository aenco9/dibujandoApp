package mx.itesm.dibujandoapp.donaciones

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandoapp.PrincipalActivity
import mx.itesm.dibujandoapp.databinding.FragmentPayPalConfirmationBinding

/**
 * Autor:
 * Luis Ignacio Ferro Salinas
 * Última modificación:
 * 18 de octubre de 2021
 */

class PayPalConfirmation : Fragment() {

    private lateinit var binding: FragmentPayPalConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPayPalConfirmationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        respondToConfirmButtonTap()
    }

    private fun respondToConfirmButtonTap() {
        binding.confirmPayPalButton.setOnClickListener {
            val myAction = PayPalConfirmationDirections.actionPayPalConfirmationToFragmentDonaciones()
            findNavController().navigate(myAction)
        }
    }
}