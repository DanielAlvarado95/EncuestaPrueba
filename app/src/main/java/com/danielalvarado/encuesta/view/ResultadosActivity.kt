package com.danielalvarado.encuesta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.danielalvarado.encuesta.R
import com.danielalvarado.encuesta.adapter.AdaptadorResultados
import com.danielalvarado.encuesta.data.database.DBPrueba
import com.danielalvarado.encuesta.data.model.Resultados
import com.danielalvarado.encuesta.databinding.ActivityResultadosBinding
import com.danielalvarado.encuesta.databinding.FragmentSumaBinding
import kotlinx.coroutines.launch

class ResultadosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultadosBinding

    var listResultados: MutableList<Resultados> = mutableListOf()

    lateinit var adaptador: AdaptadorResultados

    lateinit var room: DBPrueba

    lateinit var resultados: Resultados


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResultadosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvRes.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this, DBPrueba::class.java, "dbPruebas").build()

        obtenerresultados(room)

        binding.btnVolver.setOnClickListener {
            finish()
        }


    }

    private fun obtenerresultados(room: DBPrueba) {
        lifecycleScope.launch {
            listResultados = room.daoResultados().obtenerResultados()
            adaptador = AdaptadorResultados(listResultados)
            binding.rvRes.adapter = adaptador
        }
    }
}