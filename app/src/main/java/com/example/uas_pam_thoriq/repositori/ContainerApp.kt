package com.example.uas_pam_thoriq.repositori

import android.content.Context
import com.example.uas_pam_thoriq.data.DatabaseGreenGuardian

interface ContainerApp {
    val repositoriTanaman : RepositoriTanaman
    val repositoriSensorTanaman : RepositoriSensorTanaman
    val repositoriCatatanPemantauan : RepositoriCatatanPemantauan
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    private val database = DatabaseGreenGuardian.getDatabase(context)

    override val repositoriTanaman: RepositoriTanaman by lazy {
        OfflineRepositoriGreenGuardian(database.tanamanDao(), database.sensorDao(), database.catatanDao())
    }

    override val repositoriSensorTanaman: RepositoriSensorTanaman by lazy {
        OfflineRepositoriGreenGuardian(database.tanamanDao(), database.sensorDao(), database.catatanDao())
    }

    override val repositoriCatatanPemantauan: RepositoriCatatanPemantauan by lazy {
        OfflineRepositoriGreenGuardian(database.tanamanDao(), database.sensorDao(), database.catatanDao())
    }
}