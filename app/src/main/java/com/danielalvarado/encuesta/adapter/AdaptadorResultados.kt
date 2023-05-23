package com.danielalvarado.encuesta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.danielalvarado.encuesta.R
import com.danielalvarado.encuesta.data.model.Resultados

class AdaptadorResultados(
    val listaResultaods: MutableList<Resultados>,

) : RecyclerView.Adapter<AdaptadorResultados.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_result, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listaResultaods.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultado = listaResultaods[position]
        holder.tvResultado.text = resultado.fecha
    }


    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvResultado = itemView.findViewById<TextView>(R.id.tvResultado)
    }

}