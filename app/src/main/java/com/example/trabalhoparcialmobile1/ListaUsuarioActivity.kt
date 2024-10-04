package com.example.trabalhoparcialmobile1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabalhoparcialmobile1.databinding.ListasUsuarioBinding
import com.example.trabalhoparcialmobile1.model.CriarListaActivity
import com.example.trabalhoparcialmobile1.model.Lista

class ListaUsuarioActivity : AppCompatActivity() {

    private val REQUEST_CODE_CRIAR_LISTA = 1
    private lateinit var adapter: ListaAdapter

    // ViewModel
    private lateinit var listaViewModel: ListaViewModel

    // View Binding
    private lateinit var binding: ListasUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o binding
        binding = ListasUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar o ViewModel usando SavedStateViewModelFactory
        listaViewModel = ViewModelProvider(this, SavedStateViewModelFactory(application, this))
            .get(ListaViewModel::class.java)

        // Inicializar o RecyclerView
        binding.recyclerViewListaDeCompras.layoutManager = LinearLayoutManager(this)

        // Carregar as listas do ViewModel
        adapter = ListaAdapter(listaViewModel.getListas())
        binding.recyclerViewListaDeCompras.adapter = adapter

        // func do botao criar
        binding.criarButton.setOnClickListener {
            val intent = Intent(this, CriarListaActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CRIAR_LISTA)
        }

        // func do botao voltar
        binding.voltarButton.setOnClickListener {
            // Limpar as listas ao fazer logout
            listaViewModel.limparListas()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) // Adiciona a navegação para a tela de login
            finish() // Fecha a tela de listas
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CRIAR_LISTA && resultCode == Activity.RESULT_OK && data != null) {
            val nomeLista = data.getStringExtra("nomeLista")
            val imageUriString = data.getStringExtra("imageUri")

            if (nomeLista != null && imageUriString != null) {
                // Criar um novo objeto Lista e adicionar ao ViewModel
                val novaLista = Lista(nomeLista, imageUriString)
                listaViewModel.adicionarLista(novaLista)
                adapter.notifyDataSetChanged() // Atualizar o RecyclerView
            }
        }
    }
}
