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
import mx.itesm.dibujandoapp.viewmodel.CausasVM

class CausasFrag : Fragment(), RenglonListener {

    private val viewModel: CausasVM by viewModels()
    private lateinit var binding: FragmentCausasFragmentBinding
    val adaptador = AdaptadorListaProyectos(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCausasFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observadores, eventos, adaptador
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
        val proyecto = adaptador.arrCausas[posicion]
        println("Seleccion en proyecto $proyecto")
        val accion = CausasFragDirections.actionCausasFragToDatosDonaciones2()
        findNavController().navigate(accion)
    }
}