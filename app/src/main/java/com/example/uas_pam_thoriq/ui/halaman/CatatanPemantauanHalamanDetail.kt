package com.example.uas_pam_thoriq.ui.halaman

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam_thoriq.R
import com.example.uas_pam_thoriq.data.CatatanPemantauan
import com.example.uas_pam_thoriq.model.CatatanPemantauanDetailUiState
import com.example.uas_pam_thoriq.model.CatatanPemantauanDetailViewModel
import com.example.uas_pam_thoriq.model.PenyediaViewModel
import com.example.uas_pam_thoriq.model.toCatatanPemantauan
import com.example.uas_pam_thoriq.navigasi.DestinasiNavigasi
import com.example.uas_pam_thoriq.navigasi.GreenGuardianTopAppBar
import kotlinx.coroutines.launch

object CatatanPemantauanDetailDestination : DestinasiNavigasi {
    override val route = "CatatanPemantauan_details"
    override val titleRes = R.string.detail_catatan_pemantauan
    const val CatatanPemantauanIdArg = "CatatanPemantauanId"
    val routeWithArgs = "$route/{$CatatanPemantauanIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatatanPemantauanDetailScreen(
    navigateToEditCatatanPemantauan: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatatanPemantauanDetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            GreenGuardianTopAppBar(
                title = stringResource(CatatanPemantauanDetailDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditCatatanPemantauan(uiState.value.detailCatatanPemantauan.id) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))

            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_catatan_pemantauan),
                )
            }
        }, modifier = modifier
    ) { innerPadding ->
        CatatanPemantauanDetailsBody(
            CatatanPemantauanDetailUiState = uiState.value,
            onDelete = {
                // Note: If the user rotates the screen very fast, the operation may get cancelled
                // and the CatatanPemantauan may not be deleted from the Database. This is because when config
                // change occurs, the Activity will be recreated and the rememberCoroutineScope will
                // be cancelled - since the scope is bound to composition.
                coroutineScope.launch {
                    viewModel.deleteCatatanPemantauan()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),

            )
    }
}



@Composable
private fun CatatanPemantauanDetailsBody(
    CatatanPemantauanDetailUiState: CatatanPemantauanDetailUiState,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        CatatanPemantauanDetails(
            CatatanPemantauan  = CatatanPemantauanDetailUiState.detailCatatanPemantauan.toCatatanPemantauan(), modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.delete))
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired = false },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

@Composable
fun CatatanPemantauanDetails(
    CatatanPemantauan: CatatanPemantauan, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            CatatanPemantauanDetailsRow(
                labelResId = R.string.kondisitnm,
                CatatanPemantauanDetail = CatatanPemantauan.kondisitnm,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            CatatanPemantauanDetailsRow(
                labelResId = R.string.tglpemantau,
                CatatanPemantauanDetail = CatatanPemantauan.tglpemantau,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            CatatanPemantauanDetailsRow(
                labelResId = R.string.keterangan,
                CatatanPemantauanDetail = CatatanPemantauan.keterangan,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
        }

    }
}

@Composable
private fun CatatanPemantauanDetailsRow(
    @StringRes labelResId: Int, CatatanPemantauanDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResId))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = CatatanPemantauanDetail, fontWeight = FontWeight.Bold)
    }

}

@Composable
private fun DeleteConfirmDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(stringResource(id = R.string.attention)) },
        text = { Text(stringResource(id = R.string.delete)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(id = R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(id = R.string.yes))
            }
        })
}