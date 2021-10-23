package mx.itesm.dibujandoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mx.itesm.dibujandoapp.databinding.DatosCausaFragmentBinding

/**
 *
 * Autor:
 * Joan Daniel Guerrero García
 *
 * Última modificación:
 * 15 de octubre de 2021
 *
 * Descripción:
 * DatosCausa es el componente View de datos_causa_fragment,
 * aqui se verifica el monto que ingrese el usuario y lo
 * lleva a fragment_datos_donaciones
 *
 * */

class DatosCausa : Fragment() {

    private val args: DatosCausaArgs by navArgs()
    private lateinit var binding: DatosCausaFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DatosCausaFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTituloSeleccionado.text = args.titulo
        binding.tvDescripcionSeleccionado.text = args.descripcion

        // Se revisan los eventos...
        respondToTaps()
    }

    private fun respondToTaps() {
        // Se revisa el estado del monto ingresado y se configura el botón
        binding.donarDatosDonacionesBtn2.setOnClickListener {
            when {
                binding.montoPaypalEditTextDecimal2.text.isEmpty() -> {
                    Toast.makeText(
                        activity, "Por favor llene el campo " +
                                "del Monto antes de continuar.",
                        Toast.LENGTH_LONG).show()
                }
                binding.montoPaypalEditTextDecimal2.text.toString().toFloat() >= 9999999.99 -> {
                    binding.montoPaypalEditTextDecimal2.setText("")
                    Toast.makeText(
                        activity, "Por favor introduzca un monto" +
                                " menor a 9999999.99",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val accion = DatosCausaDirections.actionDatosCausaToDatosDonaciones(
                        binding.montoPaypalEditTextDecimal2.text.toString().toFloat(),
                        binding.tvTituloSeleccionado.text.toString()
                    )

                    findNavController().navigate(accion)
                }
            }
        }
    }
}