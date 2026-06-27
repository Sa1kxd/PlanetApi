package com.example.planetapi.presentation.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.planetapi.presentation.characters.detail.CharacterDetailScreen
import com.example.planetapi.presentation.characters.detail.CharacterDetailViewModel
import com.example.planetapi.presentation.characters.list.CharacterListScreen
import com.example.planetapi.presentation.planets.detail.DetailScreen
import com.example.planetapi.presentation.planets.list.ListScreen

@Composable
fun PlanetNavigation(
    navController: NavHostController,
    drawerState: DrawerState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.List
    ) {

        composable<Screen.List> {
            ListScreen(
                drawerState = drawerState,
                onPlanetClick = { planetId ->
                    navController.navigate(Screen.Detail(id = planetId))
                }
            )
        }

        composable<Screen.Detail> {
            DetailScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable<Screen.CharacterList> {
            CharacterListScreen(
                drawerState = drawerState,
                onCharacterClick = { characterId ->
                    navController.navigate(Screen.CharacterDetail(characterId))
                }
            )
        }

        composable<Screen.CharacterDetail> {
            val viewModel: CharacterDetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            CharacterDetailScreen(
                state = state,
                onBack = { navController.navigateUp() }
            )
        }
    }
}