package com.example.trabalhoparcialmobile1.model

data class Item(
    val nome: String,
    val quantidade: String,
    val unidade: String,
    val categoria: String,
    var comprado : Boolean = false
)