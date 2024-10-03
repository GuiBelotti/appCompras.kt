package com.example.trabalhoparcialmobile1

import androidx.lifecycle.ViewModel
import com.example.trabalhoparcialmobile1.model.Lista

class ListaViewModel : ViewModel() {

    // Lista de compras do usuário
    private val _listas: MutableList<Lista> = mutableListOf()

    // Função para obter as listas
    fun getListas(): MutableList<Lista> {
        return _listas
    }

    // Função para adicionar uma nova lista
    fun adicionarLista(lista: Lista) {
        _listas.add(lista)
    }

    // Função para limpar as listas (por exemplo, ao fazer logout)
    fun limparListas() {
        _listas.clear()
    }
}
