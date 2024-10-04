package com.example.trabalhoparcialmobile1.model

data class Lista (
    val nome: String,
    val foto: String ,
    val Itens: MutableList<Item> = mutableListOf()
) {
    companion object {
        fun add(lista: Lista, novoItem: Item) {
            lista.Itens.add(novoItem)
        }
    }
}