package mx.itesm.dibujandoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.dibujandoapp.R

class AdaptadorListaProyectos (var arrCausas: ArrayList<Proyecto>):
    RecyclerView.Adapter<AdaptadorListaProyectos.ProyectoViewHolder>()
{
    var listener: RenglonListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProyectoViewHolder {
        val vistaRenglon = LayoutInflater.from(parent.context).inflate(R.layout.renglon_causa, parent, false)
        return ProyectoViewHolder(vistaRenglon)
    }

    override fun onBindViewHolder(holder: ProyectoViewHolder, position: Int) {
        holder.set(arrCausas[position])
        val vista = holder.itemView.findViewById<LinearLayout>(R.id.layoutRenglon)
        vista.setOnClickListener{
            println("Click en Proyecto ${arrCausas[position]}")
            listener?.clickEnRenglon(position)
        }
    }

    override fun getItemCount(): Int {
        return arrCausas.size
    }

    fun actualizar(lista: List<Proyecto>?){
        arrCausas.clear()       // Liberar la memoria
        if(lista != null){
            arrCausas.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class ProyectoViewHolder(vista: View) :RecyclerView.ViewHolder(vista)
    {
        private val tvTitulo = vista.findViewById<TextView>(R.id.tvTitulo)
        private val tvDescripcion = vista.findViewById<TextView>(R.id.tvDescripcion)

        fun set(proyecto: Proyecto){
            tvTitulo.text = proyecto.Titulo
            tvDescripcion.text = proyecto.Descripcion
        }
    }
}