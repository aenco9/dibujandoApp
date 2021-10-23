package mx.itesm.dibujandoapp.donaciones

import androidx.lifecycle.ViewModel

/**
 *
 * Autor:
 * Luis Ignacio Ferro Salinas
 *
 * Última modificación:
 * 14 de octubre de 2021
 *
 * Descripción:
 * DatosDonacionesVM es el componente ViewModel de fragment_datos_donaciones,
 * aqui se manejan los métodos que maneja el modelo para revisar los tipos de
 * datos en los campos.
 *
 */

class DatosDonacionesVM: ViewModel() {
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