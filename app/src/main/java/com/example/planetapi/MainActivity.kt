package com.example.planetapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.navigation.compose.rememberNavController
import com.example.planetapi.presentation.navigation.DrawerMenu
import com.example.planetapi.presentation.navigation.PlanetNavigation
import com.example.planetapi.ui.theme.PlanetApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanetApiTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                DrawerMenu(
                    drawerState = drawerState,
                    navHostController = navController
                ) {
                    PlanetNavigation(
                        navController = navController,
                        drawerState = drawerState
                    )
                }
            }
        }
    }
}