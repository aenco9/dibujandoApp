package mx.itesm.dibujandoapp.viewmodel

/**
 *
 * Autores:
 * Eric Alexis Castañeda Bravo
 * Luis Ignacio Ferro Salinas
 *
 * Última modificación:
 * 19 de octubre de 2021
 *
 * Descripción:
 * Usuario es un data class que guarda la información que
 * se solicita en pantalla_registro_fragment, se guardan
 * los datos en forma de string para luego ser formateados como corresponda.
 *
 */

data class Usuario (var id:String="",
                    var nombre:String="",
                    var fecha:String="",
                    var correo:String="",
                    var genero: String="",
                    var telefono: String="",
                    var municipio: String=""
)