package com.example.planetapi.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.planetapi.domain.model.Planet

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onPlanetClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListBodyScreen(state = state, onEvent = viewModel::onEvent, onPlanetClick = onPlanetClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBodyScreen(
    state: ListUiState,
    onEvent: (ListEvent) -> Unit,
    onPlanetClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Planetas Dragon Ball") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            ElevatedCard(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = state.filterName,
                        onValueChange = { onEvent(ListEvent.UpdateNameFilter(it)) },
                        label = { Text("Nombre (ej. Namek)") },
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = { onEvent(ListEvent.Search) }
                    ) {
                        Text("Buscar")
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            state.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(state.planets) { planet ->
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onPlanetClick(planet.id) }
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = planet.image,
                                contentDescription = planet.name,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    planet.name,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    if (planet.isDestroyed) "🔴 Destruido" else "🟢 Existente",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun ListBodyScreenPreview() {
    val samplePlanets = listOf(
        Planet(
            id = 1,
            name = "Namek",
            isDestroyed = false,
            description = "Planeta natal de Piccolo y los Namekianos.",
            image = ""
        ),
        Planet(
            id = 2,
            name = "Vegeta",
            isDestroyed = true,
            description = "Planeta natal de los Saiyajins, destruido por Freezer.",
            image = ""
        )
    )
    val state = ListUiState(planets = samplePlanets, filterName = "")

    MaterialTheme {
        Surface {
            ListBodyScreen(
                state = state,
                onEvent = {},
                onPlanetClick = {}
            )
        }
    }
}