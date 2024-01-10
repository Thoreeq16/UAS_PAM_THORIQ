package com.example.uas_pam_thoriq.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.uas_pam_thoriq.data.Tanaman
import com.example.uas_pam_thoriq.repositori.RepositoriTanaman

class TanamanEntryViewModel(private val repositoriTanaman: RepositoriTanaman): ViewModel() {
    var uiStateTanaman by mutableStateOf(UIStateTanaman())
        private set

    private fun validasiInput(uiState: DetailTanaman = uiStateTanaman.detailTanaman ): Boolean {
        return with(uiState) {
            nmtanaman.isNotBlank() && jnstanaman.isNotBlank() && loctanaman.isNotBlank() && wkttanam.isNotBlank()
        }
    }

    fun updateUiState(detailTanaman: DetailTanaman) {
        uiStateTanaman =
            UIStateTanaman(detailTanaman = detailTanaman, isEntryValid = validasiInput(detailTanaman))
    }

    suspend fun saveTanaman() {
        if (validasiInput()) {
            repositoriTanaman.insertTanaman(uiStateTanaman.detailTanaman.toTanaman())
        }
    }
}

data class UIStateTanaman(
    val detailTanaman: DetailTanaman = DetailTanaman(),
    val isEntryValid: Boolean = false
)

data class DetailTanaman(
    val id : Int = 0,
    val nmtanaman : String ="",
    val jnstanaman : String ="",
    val loctanaman : String ="",
    val wkttanam : String ="",
    val desctanaman : String = ""
)


fun DetailTanaman.toTanaman(): Tanaman = Tanaman(
    id = id,
    nmtanaman = nmtanaman,
    jnstanaman = jnstanaman,
    loctanaman = loctanaman,
    wkttanam = wkttanam,
    desctanaman = desctanaman
)

fun Tanaman.toUiStateTanaman(isEntryValid: Boolean = false): UIStateTanaman = UIStateTanaman(
    detailTanaman = this.toDetailTanaman(),
    isEntryValid = isEntryValid
)

fun Tanaman.toDetailTanaman(): DetailTanaman = DetailTanaman(
    id = id,
    nmtanaman = nmtanaman,
    jnstanaman = jnstanaman,
    loctanaman = loctanaman,
    wkttanam = wkttanam,
    desctanaman = desctanaman
)