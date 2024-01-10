package com.example.uas_pam_thoriq.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblTanaman")
data class Tanaman(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nmtanaman : String,
    val jnstanaman : String,
    val loctanaman : String,
    val wkttanam : String,
    val desctanaman : String
)
