package com.example.uas_pam_thoriq.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_thoriq.data.Tanaman
import com.example.uas_pam_thoriq.repositori.RepositoriTanaman
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val repositoriTanaman: RepositoriTanaman) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val HomeUiState: StateFlow<HomeUiState> = repositoriTanaman.getAllTanamanStream().filterNotNull()
        .map { HomeUiState(listTanaman = it.toList()) }.stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(
                TIMEOUT_MILLIS
            ), initialValue = HomeUiState()
        )


}

data class HomeUiState(
    val listTanaman: List<Tanaman> = listOf()
)