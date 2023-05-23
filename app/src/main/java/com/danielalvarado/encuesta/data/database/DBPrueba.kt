package com.danielalvarado.encuesta.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielalvarado.encuesta.data.database.dao.UsuarioDAO
import com.danielalvarado.encuesta.data.model.Resultados

@Database(
    entities = [Resultados::class],
    version = 1
)

abstract class DBPrueba: RoomDatabase() {
    abstract fun daoResultados(): UsuarioDAO
}