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
import mx.itesm.dibujandoapp.viewmodel.LoginVM

class login : Fragment() {

    companion object {
        fun newInstance() = login()
    }

    private lateinit var viewModel: LoginVM

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= LoginFragmentBinding.inflate(layoutInflater)
        val vista= binding.root
        return vista
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Eventos

        binding.btnRegistar.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_pantallaRegistro)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginVM::class.java)
        // TODO: Use the ViewModel
    }

}