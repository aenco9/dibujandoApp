package mx.itesm.dibujandoapp.donaciones

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Autor:
 * Luis Ignacio Ferro Salinas
 * Última modificación:
 * 14 de octubre de 2021
 */

class DatosDonacionesVM: ViewModel() {
    private val modeloDonaciones: ModeloDonaciones = ModeloDonaciones()

    public fun acceptableGender(gender: String): Boolean {
        return modeloDonaciones.acceptableGender(gender)
    }

    public fun acceptableEmail(email: String): Boolean {
        return modeloDonaciones.acceptableEmail(email)
    }

    public fun acceptableDate(date: String): Boolean {
        return modeloDonaciones.acceptableDate(date)//poner funcion en modelo
    }
    public fun acceptablePhoneNumber(phoneNumber: String): Boolean {
        return modeloDonaciones.acceptablePhoneNumber(phoneNumber)
    }
}