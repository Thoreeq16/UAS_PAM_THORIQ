package com.example.uas_pam_thoriq.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_thoriq.repositori.RepositoriSensorTanaman
import com.example.uas_pam_thoriq.ui.halaman.SensorTanamanEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class SensorTanamanEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSensorTanaman: RepositoriSensorTanaman
) : ViewModel() {
    var SensorTanamanUiState by mutableStateOf(UIStateSensorTanaman())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[SensorTanamanEditDestination.SensorTanamanIdArg])

    init {
        viewModelScope.launch {
            SensorTanamanUiState = repositoriSensorTanaman.getSensorTanamanStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateSensorTanaman(true)
        }
    }

    suspend fun updateSensorTanaman() {
        if (validasiInput(SensorTanamanUiState.detailSensorTanaman)) {
            repositoriSensorTanaman.updateSensorTanaman(SensorTanamanUiState.detailSensorTanaman.toSensorTanaman())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailSensorTanaman: DetailSensorTanaman) {
        SensorTanamanUiState =
            UIStateSensorTanaman(detailSensorTanaman = detailSensorTanaman, isEntryValid = validasiInput(detailSensorTanaman))
    }

    private fun validasiInput(uiState: DetailSensorTanaman = SensorTanamanUiState.detailSensorTanaman ): Boolean {
        return with(uiState) {
            locsensor.isNotBlank() && statustanaman.isNotBlank()
        }
    }
}