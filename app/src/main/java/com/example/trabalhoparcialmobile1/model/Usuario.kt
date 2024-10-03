package com.example.trabalhoparcialmobile1.model

data class Usuario (
    val nome: String,
    val email: String,
    val senha: String,
    val listas: MutableList<Lista> = mutableListOf()
)

