package com.example.planetapi.presentation.characters.detail


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    state: CharacterDetailUiState,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del personaje") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBackIos, null)
                    }
                }
            )
        }
    ) {
            padding ->
        state.characters?.let { character ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                Text(character.name)
                Text("Raza: ${character.race}")
                Text("Genero: ${character.gender}")
                Text("Ki: ${character.ki}")
                Text("Max Ki: ${character.maxKi}")
                Text(character.description)
            }
        }
    }
}