package com.example.bibliotecando

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MinhaEstante : AppCompatActivity() {

    private lateinit var btSair: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_minha_estante)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Adiciona o padding das barras do sistema, mas garante uma margem lateral fixa de 32dp (convertida para pixels)
            val lateralPadding = (32 * resources.displayMetrics.density).toInt()
            v.setPadding(lateralPadding, systemBars.top, lateralPadding, systemBars.bottom)
            insets
        }

        btSair = findViewById(R.id.layoutLogout)

        // Referências das Abas
        val tabAndamento = findViewById<LinearLayout>(R.id.tabAndamento)
        val tabConcluidos = findViewById<LinearLayout>(R.id.tabConcluidos)
        val tabQueroLer = findViewById<LinearLayout>(R.id.tabQueroLer)

        val txtAndamento = findViewById<TextView>(R.id.txtAndamento)
        val txtConcluidos = findViewById<TextView>(R.id.txtConcluidos)
        val txtQueroLer = findViewById<TextView>(R.id.txtQueroLer)

        val lineAndamento = findViewById<View>(R.id.lineAndamento)
        val lineConcluidos = findViewById<View>(R.id.lineConcluidos)
        val lineQueroLer = findViewById<View>(R.id.lineQueroLer)

        val corAtiva = Color.parseColor("#5048E5")
        val corInativa = Color.parseColor("#A0A4B0")

        // Clique na aba Andamento
        tabAndamento.setOnClickListener {
            // Ativa Andamento
            txtAndamento.setTextColor(corAtiva)
            txtAndamento.setTypeface(null, android.graphics.Typeface.BOLD)
            lineAndamento.setBackgroundColor(corAtiva)

            // Desativa Concluídos
            txtConcluidos.setTextColor(corInativa)
            txtConcluidos.setTypeface(null, android.graphics.Typeface.NORMAL)
            lineConcluidos.setBackgroundColor(Color.TRANSPARENT)

            // Desativa Quero Ler
            txtQueroLer.setTextColor(corInativa)
            txtQueroLer.setTypeface(null, android.graphics.Typeface.NORMAL)
            lineQueroLer.setBackgroundColor(Color.TRANSPARENT)
        }

        // Clique na aba Concluídos
        tabConcluidos.setOnClickListener {
            // Desativa Andamento
            txtAndamento.setTextColor(corInativa)
            txtAndamento.setTypeface(null, android.graphics.Typeface.NORMAL)
            lineAndamento.setBackgroundColor(Color.TRANSPARENT)

            // Ativa Concluídos
            txtConcluidos.setTextColor(corAtiva)
            txtConcluidos.setTypeface(null, android.graphics.Typeface.BOLD)
            lineConcluidos.setBackgroundColor(corAtiva)

            // Desativa Quero Ler
            txtQueroLer.setTextColor(corInativa)
            txtQueroLer.setTypeface(null, android.graphics.Typeface.NORMAL)
            lineQueroLer.setBackgroundColor(Color.TRANSPARENT)
        }

        // Clique na aba Quero ler
        tabQueroLer.setOnClickListener {
            // Desativa Andamento
            txtAndamento.setTextColor(corInativa)
            txtAndamento.setTypeface(null, android.graphics.Typeface.NORMAL)
            lineAndamento.setBackgroundColor(Color.TRANSPARENT)

            // Desativa Concluídos
            txtConcluidos.setTextColor(corInativa)
            txtConcluidos.setTypeface(null, android.graphics.Typeface.NORMAL)
            lineConcluidos.setBackgroundColor(Color.TRANSPARENT)

            // Ativa Quero Ler
            txtQueroLer.setTextColor(corAtiva)
            txtQueroLer.setTypeface(null, android.graphics.Typeface.BOLD)
            lineQueroLer.setBackgroundColor(corAtiva)

        }

        // 1. Encontre o layout de logout pelo ID que criamos no XML
        btSair = findViewById<LinearLayout>(R.id.layoutLogout)

        btSair.setOnClickListener {
            // Cria um diálogo de confirmação
            android.app.AlertDialog.Builder(this)
                .setTitle("Terminar sessão")
                .setMessage("Tem certeza de que deseja sair da sua conta?")
                .setPositiveButton("Sim") { _, _ ->
                    // Ação executada se o utilizador clicar em "Sim"
                    val intent = Intent(this, Login::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Cancelar", null) // Fecha o pop-up sem fazer nada se clicar em "Cancelar"
                .show()
        }
    }
}