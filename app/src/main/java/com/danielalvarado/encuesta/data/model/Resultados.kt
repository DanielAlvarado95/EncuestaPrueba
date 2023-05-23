package com.danielalvarado.encuesta.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resultados")
data class Resultados(
    @PrimaryKey var usuario: String,
    @ColumnInfo(name = "fecha") var fecha: String
)
