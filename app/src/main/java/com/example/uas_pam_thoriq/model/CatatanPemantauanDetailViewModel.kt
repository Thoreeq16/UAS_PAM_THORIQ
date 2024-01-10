package com.example.uas_pam_thoriq.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_thoriq.repositori.RepositoriCatatanPemantauan
import com.example.uas_pam_thoriq.ui.halaman.CatatanPemantauanDetailDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CatatanPemantauanDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriCatatanPemantauan: RepositoriCatatanPemantauan
) : ViewModel() {

    private val CatatanPemantauanID: Int = checkNotNull(savedStateHandle[CatatanPemantauanDetailDestination.CatatanPemantauanIdArg])
    val uiState: StateFlow<CatatanPemantauanDetailUiState> =
        repositoriCatatanPemantauan.getCatatanPemantauanStream(CatatanPemantauanID).filterNotNull().map {
            CatatanPemantauanDetailUiState(detailCatatanPemantauan = it.toDetailCatatanPemantauan())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = CatatanPemantauanDetailUiState()
        )

    suspend fun deleteCatatanPemantauan() {
        repositoriCatatanPemantauan.deleteCatatanPemantauan(uiState.value.detailCatatanPemantauan.toCatatanPemantauan())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class  CatatanPemantauanDetailUiState(
    val outOfStock: Boolean = true,
    val detailCatatanPemantauan: DetailCatatanPemantauan = DetailCatatanPemantauan()
)