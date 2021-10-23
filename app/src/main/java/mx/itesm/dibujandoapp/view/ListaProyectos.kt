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
 * ListaProyectos es una clase la cual contiene los proyectos ya propuestos por
 * la página de Fundación Dibujando un Mañana como causas para apoyar, aqui se
 * escriben manualmente los proyectos y regresa un arreglo con todos los proyectos
 * que se crearon a partir de la lista.
 *
 * */

class ListaProyectos
{
    fun leerProyectos(): List<Proyecto> {
        return arrayListOf(
            Proyecto("Educación", "Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible."),
            Proyecto("Salud", "Garantizarles una vida sana y ejercer su bienestar físico y mental, mediante la inversión en proyectos de salud y discapacidad."),
            Proyecto("Prevención", "Promover las condiciones y los medios de vida adecuados, así como aumentar la cantidad y calidad de oportunidades para las niñas, niños y adolescentes."),
            Proyecto("Protección", "Proporcionar protección especial, mediante cuidados especializados a las niñas, niños y adolescentes víctimas de omisión de cuidados, abandono, maltrato infantil y violencia."),
            Proyecto("Género", "Empoderar a todas las niñas, así como promover la igualdad de las niñas, adolescentes y jóvenes mujeres. Esta causa engloba todas las anteriores."),
            Proyecto("Ayuda humanitaria", "Brindar respuesta a emergencias a través de la ayuda humanitaria a los afectados por los desastres naturales.")
        )
    }
}