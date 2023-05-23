package com.danielalvarado.encuesta.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielalvarado.encuesta.R
import com.danielalvarado.encuesta.databinding.ActivityDetailBinding
import com.danielalvarado.encuesta.mensaje
import com.danielalvarado.encuesta.toast

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initComponents()
    }//onCreate

    private fun initComponents() {
        binding.btnSuma.setOnClickListener {
            binding.btnSuma.setBackgroundColor(Color.BLUE)
            binding.btnSuma.setTextColor(Color.WHITE)
            bundle.putString("operacion", "suma")
            val intent = Intent(this, EncuestaActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
            animacion()
            finish()
        }//btnSuma

        binding.btnResta.setOnClickListener {
            binding.btnResta.setBackgroundColor(Color.GREEN)
            binding.btnResta.setTextColor(Color.WHITE)
            bundle.putString("operacion", "resta")
            val intent = Intent(this, EncuestaActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
            animacion()
        }//btnResta

        binding.btnMultiplicacion.setOnClickListener {
            binding.btnMultiplicacion.setBackgroundColor(Color.YELLOW)
            binding.btnMultiplicacion.setTextColor(Color.WHITE)
            bundle.putString("operacion", "multiplicacion")
            val intent = Intent(this, EncuestaActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
            animacion()
        }//btnMulti


        binding.btnHistorial.setOnClickListener {
            val intent = Intent(this, ResultadosActivity::class.java)
            startActivity(intent)
        }
    }//initComponents

    private fun animacion() {
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )
    }//animacion

}//Detailactivity