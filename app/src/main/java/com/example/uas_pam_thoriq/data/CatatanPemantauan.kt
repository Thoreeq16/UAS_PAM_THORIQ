package com.example.uas_pam_thoriq.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblPemantauan")
data class CatatanPemantauan(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val tglpemantau : String,
    val kondisitnm : String,
    val keterangan : String,
    val tanamanIdFK : Int,
    val sensorIdFK : Int
)
