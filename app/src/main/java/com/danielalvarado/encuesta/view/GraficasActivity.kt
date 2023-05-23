package com.danielalvarado.encuesta.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.danielalvarado.encuesta.R
import com.danielalvarado.encuesta.databinding.ActivityGraficasBinding
import com.danielalvarado.encuesta.showDialog
import com.echo.holographlibrary.Bar
import com.echo.holographlibrary.BarGraph
import kotlin.math.roundToInt

class GraficasActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGraficasBinding
    val puntos = ArrayList<Bar>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraficasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val operacion = bundle!!.getString("operacion", "")
        val res1 = bundle!!.getString("res1", "")
        val res2 = bundle!!.getString("res2", "")

        graficar(puntos, operacion,res1,res2)
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            //Va a iniciar el login activity como una nueva tarea
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }//handleOnBackPressed
    }//onBackPressedCallback

    private fun graficar(
        puntos: java.util.ArrayList<Bar>,
        operacion: String,
        res1: String,
        res2: String
    ) {
        val barra = Bar()
        var color = generarColorHexAleatorio()
        barra.color = Color.parseColor(color)
        barra.name = operacion
        barra.value = res1.toFloat()
        val barra2 = Bar()
        var color2 = generarColorHexAleatorio()
        barra2.color = Color.parseColor(color2)
        barra2.name = operacion
        barra2.value = res2.toFloat()

        puntos.add(barra)
        puntos.add(barra2)

        val grafica = findViewById<View>(R.id.graphBar) as BarGraph
        grafica.bars = puntos
    }

    fun generarColorHexAleatorio(): String {
        val letras = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var color = "#"
        for (i in 0..5) {
            color += letras[(Math.random() * 15).roundToInt()]
        }

        return color
    }

}