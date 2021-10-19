package mx.itesm.dibujandoapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandoapp.R
import mx.itesm.dibujandoapp.databinding.LoginFragmentBinding
import mx.itesm.dibujandoapp.databinding.PantallaRegistroFragmentBinding
import mx.itesm.dibujandoapp.donaciones.DatosDonacionesArgs
import mx.itesm.dibujandoapp.viewmodel.PantallaRegistroVM
import mx.itesm.dibujandoapp.viewmodel.Usuario

class PantallaRegistro : Fragment() {

    private lateinit var viewModel: PantallaRegistroVM
    private val args: PantallaRegistroArgs by navArgs()
    private lateinit var binding: PantallaRegistroFragmentBinding
    private lateinit var baseDatos: FirebaseDatabase
    //private val baseDatos = Firebase.database
    private val mAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PantallaRegistroFragmentBinding.inflate(layoutInflater)
        val vista = binding.root
        return vista
    }
    private fun configurarEventos() {

        binding.btnRegistar2.setOnClickListener {

            val usuarioGoogle = mAuth.currentUser

            val id = usuarioGoogle?.uid.toString()
            val nombre = binding.nombreText.text.toString()
            val fecha = binding.FechaText.text.toString()
            val correo = binding.CorreoText.text.toString()
            val usuario = Usuario(id, nombre, fecha, correo)
            val referencia = baseDatos.getReference("Usuarios/$id")
            referencia.setValue(usuario)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Eventos
        baseDatos = Firebase.database
        binding.CorreoText.setText(args.correoGoogle)
        configurarEventos()

        //binding.btnRegistar2.setOnClickListener {
        //    findNavController().navigate(R.id.action_pantallaRegistro_to_registroExitoso)
        //}
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PantallaRegistroVM::class.java)
        // TODO: Use the ViewModel
    }

}