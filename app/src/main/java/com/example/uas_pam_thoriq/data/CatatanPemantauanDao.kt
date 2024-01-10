package com.example.uas_pam_thoriq.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CatatanPemantauanDao {
    @Insert(onConflict = OnConflictStrategy. IGNORE)
    suspend fun insert(catatanPemantauan: CatatanPemantauan)

    @Update
    suspend fun update (catatanPemantauan: CatatanPemantauan)

    @Delete
    suspend fun delete (catatanPemantauan: CatatanPemantauan)

    @Query("SELECT * from tblPemantauan WHERE id = :id")
    fun getCatatanPemantauan(id: Int): Flow<CatatanPemantauan>

    @Query("SELECT * from tblPemantauan ORDER BY tglpemantau ASC")
    fun getAllCatatanPemantauan(): Flow<List<CatatanPemantauan>>
}