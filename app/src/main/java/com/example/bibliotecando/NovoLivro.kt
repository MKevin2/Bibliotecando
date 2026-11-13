
package com.example.bibliotecando

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NovoLivro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_livro)

        // Referências dos componentes
        val btVoltar = findViewById<TextView>(R.id.btVoltar)
        val btSalvarLivro = findViewById<Button>(R.id.btSalvarLivro)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etAutor = findViewById<EditText>(R.id.etAutor)
        val etTotalPaginas = findViewById<EditText>(R.id.etTotalPaginas)

        val spStatus = findViewById<Spinner>(R.id.spStatus)

        // Opções do Spinner
        val opcoesStatus = arrayOf(
            "Lendo",
            "Lido",
            "Quero ler"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            opcoesStatus
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spStatus.adapter = adapter

        // Botão voltar
        btVoltar.setOnClickListener {
            finish()
        }

        // Botão salvar
        btSalvarLivro.setOnClickListener {

            val titulo = etTitulo.text.toString().trim()
            val autor = etAutor.text.toString().trim()

            val totalPaginas =
                etTotalPaginas.text.toString().toIntOrNull()

            val status = spStatus.selectedItem.toString()

            // Validação dos campos
            if (titulo.isEmpty() || autor.isEmpty()) {

                Toast.makeText(
                    this,
                    "Preencha o título e o autor!",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (totalPaginas == null || totalPaginas <= 0) {

                Toast.makeText(
                    this,
                    "Informe uma quantidade válida de páginas!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // Preparando os dados para retornar
                val resultado = intent.apply {
                    putExtra("titulo", titulo)
                    putExtra("autor", autor)
                    putExtra("totalPaginas", totalPaginas)
                    putExtra("status", status)
                }

                setResult(RESULT_OK, resultado)

                Toast.makeText(
                    this,
                    "Livro cadastrado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}
