package mx.itesm.dibujandoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.dibujandoapp.databinding.InfoPagoFragmentBinding

class InfoPagoFrag : Fragment() {

    private lateinit var binding: InfoPagoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InfoPagoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

}