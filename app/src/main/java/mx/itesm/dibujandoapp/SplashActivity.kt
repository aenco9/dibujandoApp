package mx.itesm.dibujandoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        cargarPrincipal()
    }

    private fun cargarPrincipal(){
            Handler(Looper.getMainLooper()).postDelayed({

                cambiarPantalla()
            },500)
        }

    private fun cambiarPantalla() {
        val intPrincipal = Intent(this, PrincipalActivity::class.java)
        startActivity(intPrincipal)
        this.finish()
    }

}