package apps.nb.working.clickcounter.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import apps.nb.working.clickcounter.ui.MyBottomBar
import apps.nb.working.clickcounter.ui.screens.MainScreen
import apps.nb.working.clickcounter.ui.screens.MainViewModel
import apps.nb.working.clickcounter.ui.screens.ManualLoadingScreen
import apps.nb.working.clickcounter.ui.screens.StatefulScreen

@Composable
fun AppNavHost(appNavHostController: NavHostController = rememberNavController()) {
    val mainViewModel: MainViewModel = hiltViewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {},
        bottomBar = {
            MyBottomBar(
                { appNavHostController.navigate(HomeDestination) },
                { appNavHostController.navigate(ManualLoadingDestination) },
                { appNavHostController.navigate(StatefulDestination) },
            )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = appNavHostController,
                startDestination = HomeDestination
            ) {
                composable<HomeDestination> {
                    MainScreen()
                }
                composable<ManualLoadingDestination> {
                    ManualLoadingScreen(
                        paddingValues
                    )
                }
                composable<StatefulDestination> {
                    StatefulScreen(
                        paddingValues
                    )
                }
            }
        }
    }
}

