package mx.itesm.dibujandoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.dibujandoapp.databinding.FragmentCausasFragmentBinding
import mx.itesm.dibujandoapp.model.RenglonListener
import mx.itesm.dibujandoapp.viewmodel.CausasVM

/**
 *
 * Autor:
 * Joan Daniel Guerrero García
 *
 * Última modificación:
 * 15 de octubre de 2021
 *
 * Descripción:
 * CausasFrag es el componente View de fragment_causas_fragment,
 * aqui se recibe la lista de proyectos dados por AdaptadorListaProyectos
 * para actualizar la interfaz, además de revisar cuando el usuario
 * seleccione algun proyecto para llevarlo a la pantalla de datos_causa_fragment.
 *
 * */

class CausasFrag : Fragment(), RenglonListener {

    private val viewModel: CausasVM by viewModels()
    private val adaptador = AdaptadorListaProyectos(arrayListOf())
    private lateinit var binding: FragmentCausasFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCausasFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Se revisan los eventos...
        configurarAdaptador()
        configurarObservadores()
        configurarEventos()

        adaptador.listener = this
    }

    private fun configurarEventos() {
        viewModel.leerProyectos()
    }

    private fun configurarObservadores() {
        viewModel.arrCausas.observe(viewLifecycleOwner){lista ->
            adaptador.actualizar(lista)
        }
    }

    private fun configurarAdaptador() {
        binding.rvCausas.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = adaptador
        }
    }

    override fun clickEnRenglon(posicion: Int) {
        //Se ha seleccionado un proyecto y se redirige a donar a este proyecto
        val proyecto = adaptador.arrCausas[posicion]
        println("Seleccion en proyecto $proyecto")
        val titulo = proyecto.Titulo
        val descripcion = proyecto.Descripcion
        val accion = CausasFragDirections.actionCausasFragToDatosCausa2(titulo, descripcion)
        findNavController().navigate(accion)
    }
}