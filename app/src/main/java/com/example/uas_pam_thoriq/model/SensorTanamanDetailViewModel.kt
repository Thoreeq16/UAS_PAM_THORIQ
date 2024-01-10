package com.example.uas_pam_thoriq.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_thoriq.repositori.RepositoriSensorTanaman
import com.example.uas_pam_thoriq.ui.halaman.SensorTanamanDetailDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SensorTanamanDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSensorTanaman: RepositoriSensorTanaman
) : ViewModel() {

    private val SensorTanamanID: Int = checkNotNull(savedStateHandle[SensorTanamanDetailDestination.SensorTanamanIdArg])
    val uiState: StateFlow<SensorTanamanDetailUiState> =
        repositoriSensorTanaman.getSensorTanamanStream(SensorTanamanID).filterNotNull().map {
            SensorTanamanDetailUiState(detailSensorTanaman = it.toDetailSensorTanaman())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = SensorTanamanDetailUiState()
        )

    suspend fun deleteSensorTanaman() {
        repositoriSensorTanaman.deleteSensorTanaman(uiState.value.detailSensorTanaman.toSensorTanaman())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class  SensorTanamanDetailUiState(
    val outOfStock: Boolean = true,
    val detailSensorTanaman: DetailSensorTanaman = DetailSensorTanaman()
)