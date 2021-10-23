package mx.itesm.dibujandoapp.viewmodel

import androidx.lifecycle.ViewModel
import mx.itesm.dibujandoapp.model.ModeloDonaciones

/**
 *
 * Autor:
 * Alejandro Enriquez Coronado
 *
 * Última modificación:
 * 15 de septiembre de 2021
 *
 * Descripción:
 * PantallaRegistroVM es el componente ViewModel de pantalla_registro_fragment,
 * aquí se administran los metodos del modelo ModeloDonaciones, en el que se
 * revisan los formatos para los campos del registro de usuario.
 *
 * */

class PantallaRegistroVM : ViewModel() {
    private val modeloDonaciones: ModeloDonaciones = ModeloDonaciones()

    fun acceptableGender(gender: String): Boolean {
        return modeloDonaciones.acceptableGender(gender)
    }

    fun acceptableEmail(email: String): Boolean {
        return modeloDonaciones.acceptableEmail(email)
    }

    fun acceptableDate(date: String): Boolean {
        return modeloDonaciones.acceptableDate(date)
    }

    fun acceptablePhoneNumber(phoneNumber: String): Boolean {
        return modeloDonaciones.acceptablePhoneNumber(phoneNumber)
    }
}