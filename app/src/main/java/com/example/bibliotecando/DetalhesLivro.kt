
package com.example.bibliotecando

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.EditText
import android.app.AlertDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetalhesLivro : AppCompatActivity() {

    // Variáveis de controle
    private var paginasLidas = 120
    private var totalPaginas = 450

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_livro)

        // Referências dos componentes
        val btVoltar = findViewById<TextView>(R.id.btVoltar)
        val btAtualizarProgresso = findViewById<Button>(R.id.btAtualizarProgresso)

        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)
        val tvAutor = findViewById<TextView>(R.id.tvAutor)
        val tvProgresso = findViewById<TextView>(R.id.tvProgresso)

        val progressLeitura = findViewById<ProgressBar>(R.id.progressLeitura)

        // Recebendo informações do livro
        tvTitulo.text = intent.getStringExtra("titulo") ?: "Clean Code"
        tvAutor.text = intent.getStringExtra("autor") ?: "Robert C. Martin"

        totalPaginas = intent.getIntExtra("totalPaginas", 450)
        paginasLidas = intent.getIntExtra("paginasLidas", 120)

        progressLeitura.max = totalPaginas

        atualizarTela(tvProgresso, progressLeitura)

        // Botão voltar
        btVoltar.setOnClickListener {
            finish()
        }

        // Botão atualizar progresso
        btAtualizarProgresso.setOnClickListener {

            val campo = EditText(this)

            campo.inputType =
                android.text.InputType.TYPE_CLASS_NUMBER

            campo.hint = "Quantidade de páginas lidas"
            campo.setText(paginasLidas.toString())

            AlertDialog.Builder(this)
                .setTitle("Atualizar Progresso")
                .setMessage("Informe a quantidade de páginas lidas:")
                .setView(campo)
                .setPositiveButton("Atualizar") { _, _ ->

                    val novaPagina =
                        campo.text.toString().toIntOrNull()

                    if (novaPagina == null ||
                        novaPagina < 0 ||
                        novaPagina > totalPaginas) {

                        Toast.makeText(
                            this,
                            "Informe um número entre 0 e $totalPaginas",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        paginasLidas = novaPagina

                        atualizarTela(tvProgresso, progressLeitura)

                        Toast.makeText(
                            this,
                            "Progresso atualizado!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    // Atualiza as informações na tela
    private fun atualizarTela(
        tvProgresso: TextView,
        progressLeitura: ProgressBar
    ) {

        tvProgresso.text =
            "Progresso: $paginasLidas / $totalPaginas pág."

        progressLeitura.max = totalPaginas
        progressLeitura.progress = paginasLidas
    }
}
