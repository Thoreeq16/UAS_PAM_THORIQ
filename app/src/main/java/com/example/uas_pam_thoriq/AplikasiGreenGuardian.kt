package com.example.uas_pam_thoriq

import android.app.Application
import com.example.uas_pam_thoriq.repositori.ContainerApp
import com.example.uas_pam_thoriq.repositori.ContainerDataApp

class AplikasiGreenGuardian : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}