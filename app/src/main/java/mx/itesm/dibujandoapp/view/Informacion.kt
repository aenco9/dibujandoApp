package mx.itesm.dibujandoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.dibujandoapp.databinding.InformacionFragmentBinding

/**
 *
 * Autor:
 * Ariel Álvarez Cortés
 *
 * Última modificación:
 * 6 de octubre de 2021
 *
 * Descripción:
 * Informacion es el componente View de informacion_fragment
 *
 * */

class Informacion : Fragment() {

    private lateinit var binding: InformacionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InformacionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}