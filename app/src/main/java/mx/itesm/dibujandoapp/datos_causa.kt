package mx.itesm.dibujandoapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mx.itesm.dibujandoapp.databinding.DatosCausaFragmentBinding
import mx.itesm.dibujandoapp.databinding.FragmentDatosDonacionesBinding
import mx.itesm.dibujandoapp.donaciones.FragmentDonacionesDirections

class datos_causa : Fragment() {

    companion object {
        fun newInstance() = datos_causa()
    }

    private lateinit var binding: DatosCausaFragmentBinding
    private val args: datos_causaArgs by navArgs()
    private val viewModel: DatosCausaVM by viewModels()

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
            if (binding.montoPaypalEditTextDecimal2.text.isEmpty()) {
                Toast.makeText(getActivity(), "Por favor llene el campo " +
                        "del Monto antes de continuar.",
                    Toast.LENGTH_LONG).show()
            } else if (binding.montoPaypalEditTextDecimal2.text.toString().toFloat() >= 9999999.99) {
                binding.montoPaypalEditTextDecimal2.setText("")
                Toast.makeText(
                    getActivity(), "Por favor introduzca un monto" +
                            " menor a 9999999.99",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val accion = datos_causaDirections.actionDatosCausaToDatosDonaciones(
                    binding.montoPaypalEditTextDecimal2.text.toString().toFloat(),
                    binding.tvTituloSeleccionado.text.toString()
                )

                findNavController().navigate(accion)

            }
        }
    }
}