package mx.itesm.dibujandoapp

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import mx.itesm.dibujandoapp.databinding.ActivityPrincipalBinding

/**
 *
 * Autor:
 * Luis Ignacio Ferro Salinas
 *
 * Última modificación:
 * 19 de octubre de 2021
 *
 * Descripción:
 * PrincipalActivity es el componente View de activity_principal,
 * aqui se configura la barra lateral y se muestra el primer fragmento de navegacion.
 *
 * */

class PrincipalActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarPrincipal.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_principal)
        // Se pasa cada id del menu como un set de IDs porque cada
        // item debe ser considerado como el nivel prioritario de destinos.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.informacion_fragment, R.id.fragmentDonaciones, R.id.login, R.id.causasFrag
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Realiza el inflate en el menu; esto añade los items a la barra si esta existe.
        menuInflater.inflate(R.menu.principal, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_principal)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}