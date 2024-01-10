package com.example.uas_pam_thoriq.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_thoriq.repositori.RepositoriTanaman
import com.example.uas_pam_thoriq.ui.halaman.TanamanDetailDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TanamanDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriTanaman: RepositoriTanaman
) : ViewModel() {

    private val TanamanID: Int = checkNotNull(savedStateHandle[TanamanDetailDestination.TanamanIdArg])
    val uiState: StateFlow<TanamanDetailUiState> =
        repositoriTanaman.getTanamanStream(TanamanID).filterNotNull().map {
            TanamanDetailUiState(detailTanaman = it.toDetailTanaman())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = TanamanDetailUiState()
        )

    suspend fun deleteTanaman() {
        repositoriTanaman.deleteTanaman(uiState.value.detailTanaman.toTanaman())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class  TanamanDetailUiState(
    val outOfStock: Boolean = true,
    val detailTanaman: DetailTanaman = DetailTanaman()
)