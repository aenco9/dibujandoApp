package mx.itesm.dibujandoapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandoapp.R
import mx.itesm.dibujandoapp.databinding.PrincipalFragmentBinding
import mx.itesm.dibujandoapp.viewmodel.PrincipalVM

class Principal : Fragment() {

    companion object {
        fun newInstance() = Principal()
    }

    private lateinit var viewModel: PrincipalVM

    private lateinit var binding: PrincipalFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= PrincipalFragmentBinding.inflate(layoutInflater)
        val vista= binding.root
        return vista
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Eventos
        binding.btnInversionista.setOnClickListener {
            findNavController().navigate(R.id.action_principal_to_login)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PrincipalVM::class.java)
        // TODO: Use the ViewModel
    }

}