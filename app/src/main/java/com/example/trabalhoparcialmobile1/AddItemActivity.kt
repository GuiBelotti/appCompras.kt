package com.example.trabalhoparcialmobile1

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhoparcialmobile1.model.Item

class AddItemActivity : AppCompatActivity() {

    private lateinit var nomeEditText: EditText
    private lateinit var quantidadeEditText: EditText
    private lateinit var unidadeAutoCompleteTextView: AutoCompleteTextView
    private lateinit var categoriaAutoCompleteTextView: AutoCompleteTextView
    private lateinit var adicionarButton: Button
    private lateinit var listaDeItens: MutableList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)

        // Referenciando os componentes no layout
        nomeEditText = findViewById(R.id.textViewnome)
        quantidadeEditText = findViewById(R.id.editTextText2)
        unidadeAutoCompleteTextView = findViewById(R.id.autoCompleteTextViewUnidade)
        categoriaAutoCompleteTextView = findViewById(R.id.autoCompleteTextViewCategoria)
        adicionarButton = findViewById(R.id.button)

        // Inicializar a lista de itens
        listaDeItens = mutableListOf()

        // Configurar as opções de unidades e categorias no AutoCompleteTextView
        val unidades = arrayOf("un", "kg", "g", "L")
        val categorias = arrayOf("Fruta", "Verdura", "Carne")

        val unidadeAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, unidades)
        val categoriaAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categorias)

        unidadeAutoCompleteTextView.setAdapter(unidadeAdapter)
        categoriaAutoCompleteTextView.setAdapter(categoriaAdapter)

        // Definir o comportamento do botão de adicionar
        adicionarButton.setOnClickListener {
            adicionarItem()
        }
    }

    private fun adicionarItem() {
        // Pegar os valores inseridos nos campos
        val nome = nomeEditText.text.toString()
        val quantidade = quantidadeEditText.text.toString()
        val unidade = unidadeAutoCompleteTextView.text.toString()
        val categoria = categoriaAutoCompleteTextView.text.toString()

        // Validar se todos os campos foram preenchidos
        if (nome.isEmpty() || quantidade.isEmpty() || unidade.isEmpty() || categoria.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Criar um novo item
        val novoItem = Item(nome, quantidade, unidade, categoria)

        // Adicionar à lista
        listaDeItens.add(novoItem)

        // Mostrar a lista no log para conferir
        for (item in listaDeItens) {
            Log.d("MainActivity", "Item: $item")
        }

        // Notificar o usuário que o item foi adicionado
        Toast.makeText(this, "Item adicionado à lista", Toast.LENGTH_SHORT).show()

        // Limpar os campos após a adição
        limparCampos()
    }

    private fun limparCampos() {
        nomeEditText.text.clear()
        quantidadeEditText.text.clear()
        unidadeAutoCompleteTextView.text.clear()
        categoriaAutoCompleteTextView.text.clear()
    }
}