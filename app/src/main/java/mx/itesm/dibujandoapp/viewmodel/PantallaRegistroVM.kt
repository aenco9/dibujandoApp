package mx.itesm.dibujandoapp.viewmodel

import androidx.lifecycle.ViewModel
import mx.itesm.dibujandoapp.donaciones.ModeloDonaciones

class PantallaRegistroVM : ViewModel() {
    private val modeloDonaciones: ModeloDonaciones = ModeloDonaciones()

    fun acceptableGender(gender: String): Boolean {
        return modeloDonaciones.acceptableGender(gender)
    }

    fun acceptableEmail(email: String): Boolean {
        return modeloDonaciones.acceptableEmail(email)
    }

    fun acceptableDate(date: String): Boolean {
        return modeloDonaciones.acceptableDate(date)//poner funcion en modelo
    }
    fun acceptablePhoneNumber(phoneNumber: String): Boolean {
        return modeloDonaciones.acceptablePhoneNumber(phoneNumber)
    }}