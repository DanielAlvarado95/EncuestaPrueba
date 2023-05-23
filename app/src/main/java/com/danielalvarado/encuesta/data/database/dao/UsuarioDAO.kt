package com.danielalvarado.encuesta.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.danielalvarado.encuesta.data.model.Resultados

@Dao
interface UsuarioDAO {

    @Query("SELECT * FROM resultados")
    suspend fun obtenerResultados(): MutableList<Resultados>

    @Insert
    suspend fun agregarResultado(usuario : Resultados)


}