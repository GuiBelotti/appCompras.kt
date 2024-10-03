package com.example.trabalhoparcialmobile1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhoparcialmobile1.databinding.CadastroScreenBinding
import com.example.trabalhoparcialmobile1.model.Usuario


val usuariosCadastrados = mutableMapOf<String, Usuario>()

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: CadastroScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_screen)

        binding = CadastroScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newNameText = binding.editTextNewName
        val newEmailEditText = binding.editTextNewEmail
        val newPassword = binding.editTextNewPassword
        val confirmedNewPassword = binding.editTextConfirmedNewPassword
        val criarContaButton = binding.criarContaButton
        val exitButton = binding.exitCadastroButton

        criarContaButton.setOnClickListener {
            val digitedName = newNameText.text.toString()
            val digitedEmail = newEmailEditText.text.toString()
            val digitedPassword = newPassword.text.toString()
            val digitedConfirmedPassword = confirmedNewPassword.text.toString()


            if (digitedName.isEmpty()) {
                Toast.makeText(this, "Por favor, insira o nome", Toast.LENGTH_SHORT).show()
            } else if (digitedEmail.isEmpty()) {
                Toast.makeText(this, "Por favor, insira o e-mail", Toast.LENGTH_SHORT).show()
            } else if (digitedPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, insira a senha", Toast.LENGTH_SHORT).show()
            }else if (digitedPassword != digitedConfirmedPassword) {
                Toast.makeText(this,"As senha não coincidem", Toast.LENGTH_SHORT).show()
            }else {
                val usuario = Usuario(digitedName, digitedEmail, digitedPassword)
                usuariosCadastrados[digitedEmail] = usuario
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        exitButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
