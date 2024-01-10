package com.example.uas_pam_thoriq.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.uas_pam_thoriq.data.SensorTanaman
import com.example.uas_pam_thoriq.repositori.RepositoriSensorTanaman

class SensorTanamanEntryViewModel(private val repositoriSensorTanaman: RepositoriSensorTanaman): ViewModel() {
    var uiStateSensorTanaman by mutableStateOf(UIStateSensorTanaman())
        private set

    private fun validasiInput(uiState: DetailSensorTanaman = uiStateSensorTanaman.detailSensorTanaman ): Boolean {
        return with(uiState) {
            locsensor.isNotBlank() && statustanaman.isNotBlank()
        }
    }

    fun updateUiState(detailSensorTanaman: DetailSensorTanaman) {
        uiStateSensorTanaman =
            UIStateSensorTanaman(detailSensorTanaman = detailSensorTanaman, isEntryValid = validasiInput(detailSensorTanaman))
    }

    suspend fun saveSensorTanaman() {
        if (validasiInput()) {
            repositoriSensorTanaman.insertSensorTanaman(uiStateSensorTanaman.detailSensorTanaman.toSensorTanaman())
        }
    }
}

data class UIStateSensorTanaman(
    val detailSensorTanaman: DetailSensorTanaman = DetailSensorTanaman(),
    val isEntryValid: Boolean = false
)

data class DetailSensorTanaman(
    val id : Int = 0,
    val locsensor : String = "",
    val statustanaman : String = ""
)


fun DetailSensorTanaman.toSensorTanaman(): SensorTanaman = SensorTanaman(
    id = id,
    locsensor = locsensor,
    statustanaman = statustanaman
)

fun SensorTanaman.toUiStateSensorTanaman(isEntryValid: Boolean = false): UIStateSensorTanaman = UIStateSensorTanaman(
    detailSensorTanaman = this.toDetailSensorTanaman(),
    isEntryValid = isEntryValid
)

fun SensorTanaman.toDetailSensorTanaman(): DetailSensorTanaman = DetailSensorTanaman(
    id = id,
    locsensor = locsensor,
    statustanaman = statustanaman
)