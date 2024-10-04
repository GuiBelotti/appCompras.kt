package com.example.trabalhoparcialmobile1.model

data class Item(
    val nome: String,
    val quantidade: String,
    val unidade: String,
    val categoria: String,
    var comprado: Boolean = false // Indica se o item foi comprado
) {
    // Método para marcar o item como comprado
    fun marcarComoComprado() {
        comprado = true
    }

    // Método para obter uma descrição do item
    fun descricao(): String {
        return "$quantidade $unidade de $nome (Categoria: $categoria) - Comprado: $comprado"
    }
}