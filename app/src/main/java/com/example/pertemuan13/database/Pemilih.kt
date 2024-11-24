package com.example.pertemuan13.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pemilih_table")
data class Pemilih(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val nik: String,
    val gender: String,
    val alamat: String
)