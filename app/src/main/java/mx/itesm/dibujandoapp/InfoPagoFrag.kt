package mx.itesm.dibujandoapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import mx.itesm.dibujandoapp.databinding.FragmentCausasFragmentBinding
import mx.itesm.dibujandoapp.databinding.InfoPagoFragmentBinding
import mx.itesm.dibujandoapp.viewmodel.CausasVM

class InfoPagoFrag : Fragment() {

    companion object {
        fun newInstance() = InfoPagoFrag()
    }

    private val viewModel: InfoPagoVM by viewModels()
    private lateinit var binding: InfoPagoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InfoPagoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

}