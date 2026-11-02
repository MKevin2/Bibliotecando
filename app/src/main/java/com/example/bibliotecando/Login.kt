package com.example.bibliotecando

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btLogin: Button

    private lateinit var btSair: LinearLayout
    private lateinit var tvMensagem: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val lateralPadding = (32 * resources.displayMetrics.density).toInt()
            v.setPadding(lateralPadding, systemBars.top, lateralPadding, systemBars.bottom)
            insets
        }

        // IDs corrigidos para corresponderem exatamente ao XML
        btLogin = findViewById(R.id.btLogin)
        btSair = findViewById(R.id.layoutLogout)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        tvMensagem = findViewById(R.id.tvMensagem)

        btLogin.setOnClickListener {
            val usuario = etEmail.text.toString().trim()
            val senha = etPassword.text.toString()

            if (usuario == "biblio@mail.com" && senha == "1234") {
                val intent = Intent(this, MinhaEstante::class.java)
                startActivity(intent)
                finish()
            } else {
                tvMensagem.text = "Usuário ou Senha Inválidos!"
            }

        }

        btSair.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Finalizar aplicativo")
                .setMessage("Deseja realmente sair do aplicativo?")
                .setNegativeButton("CANCELAR", null)
                .setPositiveButton("SAIR") { _, _ ->

                    finishAffinity()
                }
                .show()
        }
    }
}