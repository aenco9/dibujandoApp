package mx.itesm.dibujandoapp.view

/**
 *
 * Autor:
 * Joan Daniel Guerrero García
 *
 * Última modificación:
 * 5 de octubre de 2021
 *
 * Descripción:
 * RenglonListener es una interface la cual declara la función de clickEnRenglon
 * que se puede usar para devolver el valor de la posición del Renglón seleccionado
 * dentro de un arreglo preestablecido. Se utiliza para revisar la posición del
 * proyecto seleccionado en el RecyclerView de ListaProyectos y con ello leer
 * su información.
 *
 * */

interface RenglonListener
{
    fun clickEnRenglon(posicion: Int)
}