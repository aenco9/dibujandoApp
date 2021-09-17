package mx.itesm.dibujandoapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandoapp.R
import mx.itesm.dibujandoapp.databinding.LoginFragmentBinding
import mx.itesm.dibujandoapp.databinding.PantallaRegistroFragmentBinding
import mx.itesm.dibujandoapp.viewmodel.PantallaRegistroVM

class PantallaRegistro : Fragment() {

    companion object {
        fun newInstance() = PantallaRegistro()
    }

    private lateinit var viewModel: PantallaRegistroVM

    private lateinit var binding: PantallaRegistroFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= PantallaRegistroFragmentBinding.inflate(layoutInflater)
        val vista= binding.root
        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Eventos
        binding.btnRegresar2.setOnClickListener {
            findNavController().navigate(R.id.action_pantallaRegistro_to_login)
        }

        binding.btnRegistar2.setOnClickListener {
            findNavController().navigate(R.id.action_pantallaRegistro_to_registroExitoso)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PantallaRegistroVM::class.java)
        // TODO: Use the ViewModel
    }

}