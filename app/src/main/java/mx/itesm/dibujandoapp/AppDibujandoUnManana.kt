package mx.itesm.dibujandoapp

import android.app.Application
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.UserAction
import com.paypal.pyplcheckout.BuildConfig

class AppDibujandoUnManana: Application() {
    override fun onCreate() {
        super.onCreate()
        val config = CheckoutConfig(
            application = this,
            clientId = "AR79_J32ZNrbgUXI6bmwcYnziWu7hM6OByiOLJN_WwfCqxuw7vLWoHLewBFl2_9vgpVL_RB0Hq3OcECq",
            environment = Environment.SANDBOX,// importar la mas corta de environment.
            returnUrl = "mx.itesm.dibujandoapp://paypalpay",
            currencyCode = CurrencyCode.MXN,
            userAction = UserAction.PAY_NOW,
            settingsConfig = SettingsConfig(
                loggingEnabled = true
            )
        )
        PayPalCheckout.setConfig(config)
    }
}