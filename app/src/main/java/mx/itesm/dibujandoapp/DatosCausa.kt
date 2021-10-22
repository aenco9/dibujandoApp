package mx.itesm.dibujandoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mx.itesm.dibujandoapp.databinding.DatosCausaFragmentBinding

class DatosCausa : Fragment() {

    private lateinit var binding: DatosCausaFragmentBinding
    private val args: DatosCausaArgs by navArgs()

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

        respondToTaps()
    }

    private fun respondToTaps() {
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