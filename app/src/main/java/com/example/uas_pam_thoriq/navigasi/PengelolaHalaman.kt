package com.example.uas_pam_thoriq.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uas_pam_thoriq.R
import com.example.uas_pam_thoriq.ui.halaman.CatatanPemantauanDestinasiEntry
import com.example.uas_pam_thoriq.ui.halaman.CatatanPemantauanDetailDestination
import com.example.uas_pam_thoriq.ui.halaman.CatatanPemantauanDetailScreen
import com.example.uas_pam_thoriq.ui.halaman.CatatanPemantauanEditDestination
import com.example.uas_pam_thoriq.ui.halaman.CatatanPemantauanEditScreen
import com.example.uas_pam_thoriq.ui.halaman.DestinasiHome
import com.example.uas_pam_thoriq.ui.halaman.EntryCatatanPemantauanScreen
import com.example.uas_pam_thoriq.ui.halaman.EntrySensorTanamanScreen
import com.example.uas_pam_thoriq.ui.halaman.EntryTanamanScreen
import com.example.uas_pam_thoriq.ui.halaman.HomeScreen
import com.example.uas_pam_thoriq.ui.halaman.SensorTanamanDestinasiEntry
import com.example.uas_pam_thoriq.ui.halaman.SensorTanamanDetailDestination
import com.example.uas_pam_thoriq.ui.halaman.SensorTanamanDetailScreen
import com.example.uas_pam_thoriq.ui.halaman.SensorTanamanEditDestination
import com.example.uas_pam_thoriq.ui.halaman.SensorTanamanEditScreen
import com.example.uas_pam_thoriq.ui.halaman.TanamanDestinasiEntry
import com.example.uas_pam_thoriq.ui.halaman.TanamanDetailDestination
import com.example.uas_pam_thoriq.ui.halaman.TanamanDetailScreen
import com.example.uas_pam_thoriq.ui.halaman.TanamanEditDestination
import com.example.uas_pam_thoriq.ui.halaman.TanamanEditScreen

@Composable
fun GreenApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreenGuardianTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onLogoutClick : () -> Unit = {},
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier, scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back
                        )
                    )
                }
            }
        }
    )
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(TanamanDestinasiEntry.route) },
                onDetailClick = { TanamanId ->
                    navController.navigate("${TanamanDestinasiEntry.route}/$TanamanId")
                }
            )
        }
        composable(TanamanDestinasiEntry.route) {
            EntryTanamanScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            TanamanDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(TanamanDetailDestination.TanamanIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val TanamanId = backStackEntry.arguments?.getInt(TanamanDetailDestination.TanamanIdArg)
            TanamanId?.let {
                TanamanDetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditTanaman = { navController.navigate("${TanamanEditDestination.route}/$it") }
                )
            }
        }

        composable(
            TanamanEditDestination.routeWithArgs,
            arguments = listOf(navArgument(TanamanEditDestination.TanamanIdArg) {
                type = NavType.IntType
            })
        ) {
            TanamanEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }


    }

    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(SensorTanamanDestinasiEntry.route) },
                onDetailClick = { SensorTanamanId ->
                    navController.navigate("${SensorTanamanDestinasiEntry.route}/$SensorTanamanId")
                }
            )
        }
        composable(SensorTanamanDestinasiEntry.route) {
            EntrySensorTanamanScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            SensorTanamanDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(SensorTanamanDetailDestination.SensorTanamanIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val SensorTanamanId = backStackEntry.arguments?.getInt(TanamanDetailDestination.TanamanIdArg)
            SensorTanamanId?.let {
                SensorTanamanDetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditSensorTanaman = { navController.navigate("${SensorTanamanEditDestination.route}/$it") }
                )
            }
        }

        composable(
            SensorTanamanEditDestination.routeWithArgs,
            arguments = listOf(navArgument(SensorTanamanEditDestination.SensorTanamanIdArg) {
                type = NavType.IntType
            })
        ) {
            SensorTanamanEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }


    }

    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(CatatanPemantauanDestinasiEntry.route) },
                onDetailClick = { CatatanPemantauanId ->
                    navController.navigate("${CatatanPemantauanDestinasiEntry.route}/$CatatanPemantauanId")
                }
            )
        }
        composable(CatatanPemantauanDestinasiEntry.route) {
            EntryCatatanPemantauanScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            CatatanPemantauanDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(CatatanPemantauanDetailDestination.CatatanPemantauanIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val CatatanPemantauanId = backStackEntry.arguments?.getInt(CatatanPemantauanDetailDestination.CatatanPemantauanIdArg)
            CatatanPemantauanId?.let {
                CatatanPemantauanDetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditCatatanPemantauan = { navController.navigate("${CatatanPemantauanEditDestination.route}/$it") }
                )
            }
        }

        composable(
            CatatanPemantauanEditDestination.routeWithArgs,
            arguments = listOf(navArgument(CatatanPemantauanEditDestination.CatatanPemantauanIdArg) {
                type = NavType.IntType
            })
        ) {
            CatatanPemantauanEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }


    }

}