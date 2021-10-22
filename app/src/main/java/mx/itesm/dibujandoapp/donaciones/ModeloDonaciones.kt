package mx.itesm.dibujandoapp.donaciones

/**
 * Autor:
 * Luis Ignacio Ferro Salinas
 * Última modificación:
 * 14 de octubre de 2021
 */

class ModeloDonaciones {
    fun acceptableGender(gender: String): Boolean {
        return gender == "M" || gender == "F" || gender == "m" || gender == "f"
    }
    fun acceptableEmail(email: String): Boolean {
        return "@" in email && "." in email
    }
    fun acceptableDate(date: String): Boolean {
        val numeroDiagonales = date.count{ "/".contains(it) }
        if (numeroDiagonales == 2 && ("." !in date) and ("-" !in date)) {
            return try {
                val listaNumeros = date.split("/")
                val day = listaNumeros[0].toInt()
                val month = listaNumeros[1].toInt()
                val year = listaNumeros[2].toInt()
                println(" dia $day mes $month ano $year \n")
                (day > 0) and
                        (day < 32) and
                        (month > 0) and
                        (month < 13) and
                        (year > 0) and
                        (year < 2022)
            } catch (e: NumberFormatException) {
                print("excepcion en el sistema")
                false
            }
        } else {
            println("diagonales mal o simbolos extranos")
            return false
        }
    }

    fun acceptablePhoneNumber(phoneNumber: String): Boolean {
        return try {
            val stripped = phoneNumber.replace(" ", "")
            stripped.matches("[0-9]+".toRegex()) and (stripped.length==10)
        } catch(e: java.lang.NumberFormatException) {
            false
        }
    }
}