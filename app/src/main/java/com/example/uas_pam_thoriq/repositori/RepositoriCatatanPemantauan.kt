package com.example.uas_pam_thoriq.repositori

import com.example.uas_pam_thoriq.data.CatatanPemantauan
import kotlinx.coroutines.flow.Flow

interface RepositoriCatatanPemantauan {
    fun getAllCatatanPemantauanStream(): Flow<List<CatatanPemantauan>>

    fun getCatatanPemantauanStream(id: Int): Flow<CatatanPemantauan?>

    suspend fun insertCatatanPemantauan(catatanPemantauan: CatatanPemantauan)

    suspend fun deleteCatatanPemantauan(catatanPemantauan: CatatanPemantauan)

    suspend fun updateCatatanPemantauan(catatanPemantauan: CatatanPemantauan)
}