package com.example.uas_pam_thoriq.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam_thoriq.R
import com.example.uas_pam_thoriq.model.CatatanPemantauanEntryViewModel
import com.example.uas_pam_thoriq.model.DetailCatatanPemantauan
import com.example.uas_pam_thoriq.model.PenyediaViewModel
import com.example.uas_pam_thoriq.model.UIStateCatatanPemantauan
import com.example.uas_pam_thoriq.navigasi.DestinasiNavigasi
import com.example.uas_pam_thoriq.navigasi.GreenGuardianTopAppBar
import kotlinx.coroutines.launch

object CatatanPemantauanDestinasiEntry : DestinasiNavigasi {
    override val route = "CatatanPemantauan_entry"
    override val titleRes = R.string.entry_catatan_pemantauan
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryCatatanPemantauanScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatatanPemantauanEntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            GreenGuardianTopAppBar(
                title = stringResource(CatatanPemantauanDestinasiEntry.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        EntryCatatanPemantauanBody(
            uiStateCatatanPemantauan = viewModel.uiStateCatatanPemantauan,
            onCatatanPemantauanValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveCatatanPemantauan()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryCatatanPemantauanBody(
    uiStateCatatanPemantauan: UIStateCatatanPemantauan,
    onCatatanPemantauanValueChange: (DetailCatatanPemantauan) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        FormInputCatatanPemantauan(
            detailCatatanPemantauan = uiStateCatatanPemantauan.detailCatatanPemantauan,
            onValueChange = onCatatanPemantauanValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateCatatanPemantauan.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputCatatanPemantauan(
    detailCatatanPemantauan: DetailCatatanPemantauan,
    modifier: Modifier = Modifier,
    onValueChange: (DetailCatatanPemantauan) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = detailCatatanPemantauan.kondisitnm,
            onValueChange = { onValueChange(detailCatatanPemantauan.copy(kondisitnm = it)) },
            label = { Text(stringResource(R.string.kondisitnm)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailCatatanPemantauan.tglpemantau,
            onValueChange = { onValueChange(detailCatatanPemantauan.copy(tglpemantau = it)) },
            label = { Text(stringResource(R.string.tglpemantau)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailCatatanPemantauan.keterangan,
            onValueChange = { onValueChange(detailCatatanPemantauan.copy(keterangan = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.keterangan)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = stringResource(id = R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.padding_small),
            modifier = Modifier.padding(
                bottom = dimensionResource(
                    id = R.dimen.padding_medium
                )
            )
        )
    }
}