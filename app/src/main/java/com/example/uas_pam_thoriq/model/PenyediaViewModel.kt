package com.example.uas_pam_thoriq.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uas_pam_thoriq.AplikasiGreenGuardian

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiGreenGuardian().container.repositoriTanaman)
        }

        initializer {
            TanamanEntryViewModel(aplikasiGreenGuardian().container.repositoriTanaman)
        }

        initializer {
            TanamanDetailViewModel(createSavedStateHandle(), aplikasiGreenGuardian().container.repositoriTanaman)
        }

        initializer {
            TanamanEditViewModel(
                createSavedStateHandle(),
                aplikasiGreenGuardian().container.repositoriTanaman,
            )
        }

        initializer {
            SensorTanamanEntryViewModel(aplikasiGreenGuardian().container.repositoriSensorTanaman)
        }
        initializer {
            SensorTanamanDetailViewModel(createSavedStateHandle(),aplikasiGreenGuardian().container.repositoriSensorTanaman)
        }
        initializer {
            SensorTanamanEditViewModel(createSavedStateHandle(),aplikasiGreenGuardian().container.repositoriSensorTanaman)
        }

        initializer {
            CatatanPemantauanEntryViewModel(aplikasiGreenGuardian().container.repositoriCatatanPemantauan)
        }
        initializer {
            CatatanPemantauanDetailViewModel(createSavedStateHandle(),aplikasiGreenGuardian().container.repositoriCatatanPemantauan)
        }
        initializer {
            CatatanPemantauanEditViewModel(createSavedStateHandle(),aplikasiGreenGuardian().container.repositoriCatatanPemantauan)
        }
    }
}
fun CreationExtras.aplikasiGreenGuardian(): AplikasiGreenGuardian = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiGreenGuardian)