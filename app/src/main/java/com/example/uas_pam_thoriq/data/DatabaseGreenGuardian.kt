package com.example.uas_pam_thoriq.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tanaman::class, SensorTanaman::class, CatatanPemantauan::class], version = 1, exportSchema = false)
abstract class DatabaseGreenGuardian : RoomDatabase() {
    abstract fun tanamanDao(): TanamanDao
    abstract fun sensorDao():SensorTanamanDao
    abstract fun catatanDao(): CatatanPemantauanDao

    companion object {
        @Volatile
        private var Instance: DatabaseGreenGuardian? = null

        fun getDatabase(context: Context): DatabaseGreenGuardian {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DatabaseGreenGuardian::class.java, "DatabaseGreenGuardian").build()
                    .also { Instance = it }
            })
        }
    }
}