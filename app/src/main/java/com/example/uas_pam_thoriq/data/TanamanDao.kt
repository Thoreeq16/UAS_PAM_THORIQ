package com.example.uas_pam_thoriq.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TanamanDao {
    @Insert(onConflict = OnConflictStrategy. IGNORE)
    suspend fun insert(tanaman: Tanaman)

    @Update
    suspend fun update (tanaman: Tanaman)

    @Delete
    suspend fun delete (tanaman: Tanaman)

    @Query("SELECT * from tblTanaman WHERE id = :id")
    fun getTanaman(id: Int): Flow<Tanaman>

    @Query("SELECT * from tblTanaman ORDER BY nmtanaman ASC")
    fun getAllTanaman(): Flow<List<Tanaman>>
}