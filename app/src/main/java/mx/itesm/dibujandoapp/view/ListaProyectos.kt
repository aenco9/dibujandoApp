package mx.itesm.dibujandoapp.view

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