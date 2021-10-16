package mx.itesm.dibujandoapp.donaciones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.dibujandoapp.databinding.FragmentPayPalConfirmationBinding


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
}