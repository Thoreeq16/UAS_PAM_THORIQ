package com.example.uas_pam_thoriq.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.uas_pam_thoriq.data.CatatanPemantauan
import com.example.uas_pam_thoriq.repositori.RepositoriCatatanPemantauan

class CatatanPemantauanEntryViewModel(private val repositoriCatatanPemantauan: RepositoriCatatanPemantauan): ViewModel() {
    var uiStateCatatanPemantauan by mutableStateOf(UIStateCatatanPemantauan())
        private set

    private fun validasiInput(uiState: DetailCatatanPemantauan = uiStateCatatanPemantauan.detailCatatanPemantauan ): Boolean {
        return with(uiState) {
            tglpemantau.isNotBlank() && kondisitnm.isNotBlank() && keterangan.isNotBlank()
        }
    }

    fun updateUiState(detailCatatanPemantauan: DetailCatatanPemantauan) {
        uiStateCatatanPemantauan =
            UIStateCatatanPemantauan(detailCatatanPemantauan = detailCatatanPemantauan, isEntryValid = validasiInput(detailCatatanPemantauan))
    }

    suspend fun saveCatatanPemantauan() {
        if (validasiInput()) {
            repositoriCatatanPemantauan.insertCatatanPemantauan(uiStateCatatanPemantauan.detailCatatanPemantauan.toCatatanPemantauan())
        }
    }
}

data class UIStateCatatanPemantauan(
    val detailCatatanPemantauan: DetailCatatanPemantauan = DetailCatatanPemantauan(),
    val isEntryValid: Boolean = false
)

data class DetailCatatanPemantauan(
    val id : Int = 0,
    val tglpemantau : String ="",
    val kondisitnm : String ="",
    val keterangan : String ="",
    val tanamanIdFK : Int = 0,
    val sensorIdFK : Int = 0
)


fun DetailCatatanPemantauan.toCatatanPemantauan(): CatatanPemantauan = CatatanPemantauan(
    id = id,
    tglpemantau = tglpemantau,
    kondisitnm = kondisitnm,
    keterangan = keterangan,
    tanamanIdFK = tanamanIdFK,
    sensorIdFK = sensorIdFK
)

fun CatatanPemantauan.toUiStateCatatanPemantauan(isEntryValid: Boolean = false): UIStateCatatanPemantauan = UIStateCatatanPemantauan(
    detailCatatanPemantauan = this.toDetailCatatanPemantauan(),
    isEntryValid = isEntryValid
)

fun CatatanPemantauan.toDetailCatatanPemantauan(): DetailCatatanPemantauan = DetailCatatanPemantauan(
    id = id,
    tglpemantau = tglpemantau,
    kondisitnm = kondisitnm,
    keterangan = keterangan,
    tanamanIdFK = tanamanIdFK,
    sensorIdFK = sensorIdFK
)