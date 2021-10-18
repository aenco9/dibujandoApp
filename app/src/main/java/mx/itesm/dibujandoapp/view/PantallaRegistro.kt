package mx.itesm.dibujandoapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandoapp.R
import mx.itesm.dibujandoapp.databinding.LoginFragmentBinding
import mx.itesm.dibujandoapp.databinding.PantallaRegistroFragmentBinding
import mx.itesm.dibujandoapp.viewmodel.PantallaRegistroVM
import mx.itesm.dibujandoapp.viewmodel.Usuario

class PantallaRegistro : Fragment() {

    companion object {
        fun newInstance() = PantallaRegistro()
    }

    private lateinit var viewModel: PantallaRegistroVM

    private lateinit var binding: PantallaRegistroFragmentBinding
    private val baseDatos = Firebase.database
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= PantallaRegistroFragmentBinding.inflate(layoutInflater)
        val vista= binding.root
        confiugurarEventos()
        return vista
    }

    private fun confiugurarEventos() {
        binding.btnRegistar2.setOnClickListener {

            //Se tiene que modificar el Usuario.kt por que falta el ID que da google y ponerlo abajo 
            val nombre = binding.nombreText.text.toString()
            val fecha = binding.FechaText.text.toString()
            val correo = binding.CorreoText.text.toString()

            val usuario = Usuario(nombre,fecha,correo)


            //aqui tenemos que poner el ID recuperado que da google para que sea la PK no el nombre
            val referencia = baseDatos.getReference("Usuarios/$nombre")
            referencia.setValue(usuario)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Eventos


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