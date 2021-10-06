package mx.itesm.dibujandoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.dibujandoapp.view.ListaProyectos
import mx.itesm.dibujandoapp.view.Proyecto

class CausasVM : ViewModel()
{
    private val modelo = ListaProyectos()

    val arrCausas = MutableLiveData<List<Proyecto>>()

    fun leerProyectos(){
        arrCausas.value = modelo.leerProyectos()
    }
}