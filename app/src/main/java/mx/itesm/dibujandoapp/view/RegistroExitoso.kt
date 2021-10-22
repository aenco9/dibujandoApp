package mx.itesm.dibujandoapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandoapp.databinding.RegistroExitosoFragment2Binding

import mx.itesm.dibujandoapp.viewmodel.RegistroExitosoVM

class RegistroExitoso : Fragment() {

    lateinit var viewModel: RegistroExitosoVM
    private lateinit var binding: RegistroExitosoFragment2Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegistroExitosoFragment2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Eventos
        binding.btnEntendido.setOnClickListener {
            findNavController().navigate(RegistroExitosoDirections.actionRegistroExitosoToLogin())
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistroExitosoVM::class.java)
    }

}