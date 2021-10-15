package mx.itesm.dibujandoapp.donaciones

import android.widget.Toast
import com.google.android.material.internal.ContextUtils.getActivity

/**
 * Autor:
 * Luis Ignacio Ferro Salinas
 * Última modificación:
 * 14 de octubre de 2021
 */

class ModeloDonaciones {
    public fun acceptableGender(gender: String): Boolean {
        return gender == "M" || gender == "F" || gender == "m" || gender == "f"
    }
    public fun acceptableEmail(email: String): Boolean {
        return "@" in email && "." in email
    }
    public fun acceptableDate(date: String): Boolean {
        return true
    }
}