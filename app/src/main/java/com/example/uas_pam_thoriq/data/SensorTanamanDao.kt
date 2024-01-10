package com.example.uas_pam_thoriq.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SensorTanamanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sensorTanaman: SensorTanaman)

    @Update
    suspend fun update (sensorTanaman: SensorTanaman)

    @Delete
    suspend fun delete (sensorTanaman: SensorTanaman)

    @Query("SELECT * from tblSensor WHERE id = :id")
    fun getSensorTanaman(id: Int): Flow<SensorTanaman>

    @Query("SELECT * from tblSensor ORDER BY locsensor ASC")
    fun getAllSensorTanaman(): Flow<List<SensorTanaman>>
}