package com.example.uas_pam_thoriq.ui.halaman

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam_thoriq.R
import com.example.uas_pam_thoriq.model.PenyediaViewModel
import com.example.uas_pam_thoriq.model.SensorTanamanEditViewModel
import com.example.uas_pam_thoriq.navigasi.DestinasiNavigasi
import com.example.uas_pam_thoriq.navigasi.GreenGuardianTopAppBar
import kotlinx.coroutines.launch

object SensorTanamanEditDestination : DestinasiNavigasi {
    override val route = "SensorTanaman_edit"
    override val titleRes = R.string.edit_sensor_tanaman
    const val SensorTanamanIdArg = "SensorTanamanId"
    val routeWithArgs = "$route/{$SensorTanamanIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorTanamanEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SensorTanamanEditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            GreenGuardianTopAppBar(
                title = stringResource(SensorTanamanEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntrySensorTanamanBody(
            uiStateSensorTanaman = viewModel.SensorTanamanUiState,
            onSensorTanamanValueChange = viewModel::updateUiState,
            onSaveClick = {
                // Note: If the user rotates the screen very fast, the operation may get cancelled
                // and the SensorTanaman may not be updated in the Database. This is because when config
                // change occurs, the Activity will be recreated and the rememberCoroutineScope will
                // be cancelled - since the scope is bound to composition.
                coroutineScope.launch {
                    viewModel.updateSensorTanaman()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}