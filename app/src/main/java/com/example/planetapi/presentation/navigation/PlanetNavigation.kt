package com.example.planetapi.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planetapi.presentation.detail.DetailScreen
import com.example.planetapi.presentation.list.ListScreen
@Composable
fun PlanetNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.List
    ) {

        composable<Screen.List> {
            ListScreen(
                onPlanetClick = { planetId ->
                    navController.navigate(Screen.Detail(id = planetId))
                }
            )
        }

        composable<Screen.Detail> {
            DetailScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}