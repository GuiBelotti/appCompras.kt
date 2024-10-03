package com.example.trabalhoparcialmobile1

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabalhoparcialmobile1.model.Lista

class ListaAdapter(private val listas: List<Lista>) : RecyclerView.Adapter<ListaAdapter.ListaViewHolder>() {

    // ViewHolder para cada item da lista
    class ListaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeLista: TextView = itemView.findViewById(R.id.textViewNomeLista)
        val fotoLista: ImageView = itemView.findViewById(R.id.imageViewLista)
    }

    // converte xml em hierarquia de views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return ListaViewHolder(view)
    }

    // Vincula os dados da lista ao ViewHolder
    override fun onBindViewHolder(holder: ListaViewHolder, position: Int) {
        val lista = listas[position]
        holder.nomeLista.text = lista.nome

        if (lista.foto.isNullOrEmpty()) {
            // Carregar a imagem padrão se não houver URI de imagem
            holder.fotoLista.setImageResource(R.drawable.ic_launcher_background)
            // carrega a imagem pelo glide
        }
        val imageUri = Uri.parse(lista.foto)
        Glide.with(holder.itemView.context)
            .load(imageUri)
            .placeholder(R.drawable.ic_launcher_background) // Imagem padrao
            .into(holder.fotoLista)
    }

    // Retorna o número de itens na lista
    override fun getItemCount(): Int {
        return listas.size
    }
}
