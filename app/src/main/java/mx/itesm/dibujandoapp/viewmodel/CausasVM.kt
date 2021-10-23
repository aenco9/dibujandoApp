package mx.itesm.dibujandoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.dibujandoapp.view.ListaProyectos
import mx.itesm.dibujandoapp.view.Proyecto

/**
 *
 * Autor:
 * Joan Daniel Guerrero García
 *
 * Última modificación:
 * 5 de octubre de 2021
 *
 * Descripción:
 * CausasVM es el componente ViewModel de fragment_causas_fragment,
 * aquí se administra el método de leerProyectos que está en el modelo ListaProyectos
 * y se registra el valor del arreglo de proyectos por medio de un Mutable Live Data
 * al cual está registrado el view CausasFrag.
 *
 * */

class CausasVM : ViewModel()
{
    val arrCausas = MutableLiveData<List<Proyecto>>()
    private val modelo = ListaProyectos()

    fun leerProyectos(){
        arrCausas.value = modelo.leerProyectos()
    }
}