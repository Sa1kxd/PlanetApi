package com.example.planetapi.presentation.planets.list
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planetapi.domain.usecases.GetPlanetUseCase
import com.example.planetapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListUiState())
    val state = _state.asStateFlow()

    init {
        loadPlanets()
    }

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.UpdateNameFilter -> {
                _state.update { it.copy(filterName = event.name) }
            }
            ListEvent.Search -> loadPlanets()
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            val current = _state.value

            getPlanetsUseCase(
                name = current.filterName.takeIf { it.isNotBlank() }
            ).collect { result ->
                when (result) {
                    is Resource.Loading ->
                        _state.update { it.copy(isLoading = true) }

                    is Resource.Success ->
                        _state.update {
                            it.copy(isLoading = false, planets = result.data ?: emptyList())
                        }

                    is Resource.Error ->
                        _state.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                }
            }
        }
    }
}