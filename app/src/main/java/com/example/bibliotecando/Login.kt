package com.example.bibliotecando

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Adiciona o padding das barras do sistema, mas garante uma margem lateral fixa de 32dp (convertida para pixels)
            val lateralPadding = (32 * resources.displayMetrics.density).toInt()
            v.setPadding(lateralPadding, systemBars.top, lateralPadding, systemBars.bottom)
            insets
        }

        val btnLogin = findViewById<android.widget.Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            // Cria a intenção de ir da Login para a EstanteActivity
            val intent = Intent(this, MinhaEstante::class.java)
            startActivity(intent)

            // Opcional: fecha a tela de login para que o usuário não volte para ela ao apertar o botão "voltar" do celular
            finish()
        }

    }
}