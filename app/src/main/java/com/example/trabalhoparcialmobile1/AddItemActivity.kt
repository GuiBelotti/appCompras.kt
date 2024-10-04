package com.example.trabalhoparcialmobile1

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhoparcialmobile1.model.Item
import com.example.trabalhoparcialmobile1.model.Lista

class AddItemActivity : AppCompatActivity() {

    private lateinit var nomeEditText: EditText
    private lateinit var quantidadeEditText: EditText
    private lateinit var unidadeSpinner: Spinner
    private lateinit var categoriaSpinner: Spinner
    private lateinit var adicionarButton: Button
    private lateinit var lista: Lista // Instância da classe Lista

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)

        // Referenciando os componentes no layout
        nomeEditText = findViewById(R.id.editTextText)
        quantidadeEditText = findViewById(R.id.editTextText2)
        unidadeSpinner = findViewById(R.id.spinnerUnidade)
        categoriaSpinner = findViewById(R.id.spinnerCategoria)
        adicionarButton = findViewById(R.id.button)

        // Inicializar a lista de itens com um nome e uma foto (caso necessário)
        lista = Lista(nome = "Minha Lista", foto = "") // Cria uma nova instância de Lista

        // Configurar as opções de unidades e categorias nos Spinners
        val unidades = arrayOf("Unidade", "Quilogramas", "Gramas", "Litros")
        val categorias = arrayOf("Fruta", "Verdura", "Carne")

        val unidadeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unidades)
        unidadeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unidadeSpinner.adapter = unidadeAdapter

        val categoriaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categoriaSpinner.adapter = categoriaAdapter

        // Definir o comportamento do botão de adicionar
        adicionarButton.setOnClickListener {
            adicionarItem()
        }
    }

    private fun adicionarItem() {
        // Pegar os valores inseridos nos campos
        val nome = nomeEditText.text.toString()
        val quantidade = quantidadeEditText.text.toString()
        val unidade = unidadeSpinner.selectedItem.toString()
        val categoria = categoriaSpinner.selectedItem.toString()

        // Validar se todos os campos foram preenchidos
        if (nome.isEmpty() || quantidade.isEmpty() || unidade.isEmpty() || categoria.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Criar um novo item
        val novoItem = Item(nome, quantidade, unidade, categoria)

        // Adicionar à lista usando o método do companion object
        Lista.add(lista, novoItem) // Chama o método de adição

        // Mostrar a lista para conferir
        Log.d("AddItemActivity", "Item adicionado: ${novoItem.descricao()}")

        // Notificar o usuário que o item foi adicionado
        Toast.makeText(this, "Item adicionado à lista", Toast.LENGTH_SHORT).show()

        // Limpar os campos após a adição
        limparCampos()
    }

    private fun limparCampos() {
        nomeEditText.text.clear()
        quantidadeEditText.text.clear()
        unidadeSpinner.setSelection(0) // Resetar o Spinner para a primeira opção
        categoriaSpinner.setSelection(0) // Resetar o Spinner para a primeira opção
    }
}
