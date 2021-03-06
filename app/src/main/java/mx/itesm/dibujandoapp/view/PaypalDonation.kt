package mx.itesm.dibujandoapp.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import mx.itesm.dibujandoapp.databinding.FragmentPaypalDonationBinding

/**
 * Autor:
 * Luis Ignacio Ferro Salinas
 *
 * Última fecha de moficación:
 * 14 de octubre de 2021.
 *
 * Descripcion:
 * PaypalDonation es el componente View de fragment_paypal_donation,
 * aqui se revisa la interacción del usuario con el botón de la
 * interfaz y se realiza el pago en Paypal.
 *
 */

class PaypalDonation: Fragment() {

    private val args: PaypalDonationArgs by navArgs()
    private lateinit var binding: FragmentPaypalDonationBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaypalDonationBinding.inflate(layoutInflater)
        pagoPaypal()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Se revisan los eventos...
        showAmount()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun pagoPaypal() {
        val payPalButton = binding.actualPayPalButton
        payPalButton.setup(
            createOrder = CreateOrder { createOrderActions ->
                val order = Order(
                    intent = OrderIntent.CAPTURE,
                    appContext = AppContext(
                        brandName = "Fundación Dibujando un Mañana",
                        locale = "es-MX",
                        landingPage = "NO_PREFERENCE",
                        userAction = UserAction.PAY_NOW
                    ),
                    purchaseUnitList = listOf(
                        PurchaseUnit(
                            amount = Amount(
                                currencyCode = CurrencyCode.MXN,
                                value = String.format("%.1f", args.monto)
                                // Pasando el monto a paypal.
                            )
                        )
                    )
                )

                createOrderActions.create(order)
            },
            onApprove = OnApprove { approval ->
                approval.orderActions.capture {
                    val actionToConfirm = PaypalDonationDirections
                        .actionPaypalDonationToPayPalConfirmation()
                    findNavController().navigate(actionToConfirm)
                }
            },
            onCancel = OnCancel {
                Log.d("OnCancel", "Buyer cancelled the PayPal experience.")
                println("Buyer cancelled the PayPal experience.")
            },
            onError = OnError { errorInfo ->
                Log.d("OnError", "Error: $errorInfo")
                println("Error: $errorInfo")
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun showAmount() {
        binding.showAmountTextView.text = "Usted va a donar: $ ${args.monto} MXN a ${args.titulo}"
    }
}