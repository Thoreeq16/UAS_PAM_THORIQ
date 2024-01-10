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
import com.example.uas_pam_thoriq.model.CatatanPemantauanEditViewModel
import com.example.uas_pam_thoriq.model.PenyediaViewModel
import com.example.uas_pam_thoriq.navigasi.DestinasiNavigasi
import com.example.uas_pam_thoriq.navigasi.GreenGuardianTopAppBar
import kotlinx.coroutines.launch

object CatatanPemantauanEditDestination : DestinasiNavigasi {
    override val route = "CatatanPemantauan_edit"
    override val titleRes = R.string.edit_catatan_pemantauan
    const val CatatanPemantauanIdArg = "CatatanPemantauanId"
    val routeWithArgs = "$route/{$CatatanPemantauanIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatatanPemantauanEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatatanPemantauanEditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            GreenGuardianTopAppBar(
                title = stringResource(CatatanPemantauanEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryCatatanPemantauanBody(
            uiStateCatatanPemantauan = viewModel.CatatanPemantauanUiState,
            onCatatanPemantauanValueChange = viewModel::updateUiState,
            onSaveClick = {
                // Note: If the user rotates the screen very fast, the operation may get cancelled
                // and the CatatanPemantauan may not be updated in the Database. This is because when config
                // change occurs, the Activity will be recreated and the rememberCoroutineScope will
                // be cancelled - since the scope is bound to composition.
                coroutineScope.launch {
                    viewModel.updateCatatanPemantauan()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}