package com.example.trabalhoparcialmobile1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhoparcialmobile1.databinding.LoginScreenBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emailEditText = binding.editTextTextEmailAddressEmailLogin
        val passwordEditText = binding.editTextTextLoginPassword
        val loginButton = binding.loginButton
        val registerButton = binding.cadastroButton

        //botão de login
        loginButton.setOnClickListener {
            val digitedEmail = emailEditText.text.toString()
            val digitedPassword = passwordEditText.text.toString()

            if (digitedEmail.isEmpty()) {
                Toast.makeText(this, "Por favor, insira o e-mail", Toast.LENGTH_SHORT).show()
            } else if (digitedPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, insira a senha", Toast.LENGTH_SHORT).show()
            } else {

                val usuario = usuariosCadastrados[digitedEmail]

                if (usuario != null && usuario.senha == digitedPassword) {
                    Toast.makeText(this, "Login realizado com sucesso! Bem-vindo, ${usuario.nome}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ListaUsuarioActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                }
            }
        }
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
