package mx.itesm.dibujandoapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import mx.itesm.dibujandoapp.R

/**
 *
 * Autor:
 * Eric Alexis Castañeda Bravo
 *
 * Última modificación:
 * 7 de octubre de 2021
 *
 * Descripción:
 * SplashActivity es el componente View de activity_splash,
 * aqui se realiza el efecto de Splash donde se muestra el
 * logo principal y luego se cambia a activity_principal.
 *
 * */

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