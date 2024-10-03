package com.example.trabalhoparcialmobile1.model

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhoparcialmobile1.R

class CriarListaActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_lista)

        val buttonAdicionarImagem = findViewById<Button>(R.id.buttonAdicionarImagem)
        val buttonCriarLista = findViewById<Button>(R.id.buttonCriarLista)
        val editTextNomeLista = findViewById<EditText>(R.id.editTextNomeLista)

        // abre galeria
        buttonAdicionarImagem.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        // Criar a lista e retornar para a tela anterior
        buttonCriarLista.setOnClickListener {
            val nomeLista = editTextNomeLista.text.toString()

            //erro se nome da lista for vazio
            if (nomeLista.isEmpty()) {
                return@setOnClickListener
            }

            // Retornar dados da lista para activity de listas do usuario
            val resultIntent = Intent()

            //nome
            resultIntent.putExtra("nomeLista", nomeLista)

            //imagem
            resultIntent.putExtra("imageUri", imageUri?.toString())

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {

            //pega URI da imagem selecionada
            imageUri = data.data
            val imageViewPreview = findViewById<ImageView>(R.id.imageViewPreview)

            //mostra imagem no imageview
            imageViewPreview.setImageURI(imageUri)
        }
    }
}
