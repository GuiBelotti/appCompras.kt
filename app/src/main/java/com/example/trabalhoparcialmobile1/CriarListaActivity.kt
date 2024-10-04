package com.example.trabalhoparcialmobile1.model

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhoparcialmobile1.R

class CriarListaActivity : AppCompatActivity() {

    private var imageUri: Uri? = null
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_lista)

        val buttonAdicionarImagem = findViewById<Button>(R.id.buttonAdicionarImagem)
        val buttonCriarLista = findViewById<Button>(R.id.buttonCriarLista)
        val editTextNomeLista = findViewById<EditText>(R.id.editTextNomeLista)
        val imageViewPreview = findViewById<ImageView>(R.id.imageViewPreview)

        // Registrar o launcher para pegar o resultado da galeria
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                // Pega o URI da imagem selecionada
                imageUri = result.data?.data
                // Mostra a imagem no ImageView
                imageViewPreview.setImageURI(imageUri)
            }
        }

        // Abre a galeria
        buttonAdicionarImagem.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImageLauncher.launch(intent) // Inicia a atividade usando o launcher
        }

        // Criar a lista e retornar para a tela anterior
        buttonCriarLista.setOnClickListener {
            val nomeLista = editTextNomeLista.text.toString()

            // Erro se o nome da lista for vazio
            if (nomeLista.isEmpty()) {
                return@setOnClickListener
            }

            // Retornar dados da lista para a activity de listas do usuário
            val resultIntent = Intent()

            // Nome
            resultIntent.putExtra("nomeLista", nomeLista)

            // Verificar se o imageUri é nulo
            if (imageUri == null) {
                // Se o usuário não selecionar uma imagem, use a imagem padrão
                val defaultImageUri = Uri.parse("android.resource://" + packageName + "/" + R.drawable.ic_launcher_background)
                resultIntent.putExtra("imageUri", defaultImageUri.toString())
            } else {
                // Se o usuário selecionar uma imagem, use o URI da imagem selecionada
                resultIntent.putExtra("imageUri", imageUri.toString())
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
