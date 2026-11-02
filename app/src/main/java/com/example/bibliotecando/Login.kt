package com.example.bibliotecando

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
    }
}