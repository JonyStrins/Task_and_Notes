package com.example.taskandnotes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "titulo") val titulo: String?,
    @ColumnInfo(name = "descripcion") val descripcion: String?,
    @ColumnInfo(name = "foto") val foto: String?,
    @ColumnInfo(name = "video") val video: String?,
    @ColumnInfo(name = "fecha") val fecha: Int
)
