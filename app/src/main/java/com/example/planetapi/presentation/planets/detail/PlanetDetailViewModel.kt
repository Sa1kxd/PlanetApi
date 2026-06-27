package com.example.planetapi.presentation.planets.detail
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.planetapi.domain.usecases.GetPlanetDetailUseCase
import com.example.planetapi.presentation.navigation.Screen
import com.example.planetapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPlanetDetailUseCase: GetPlanetDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<Screen.Detail>()
        loadPlanet(args.id)
    }

    private fun loadPlanet(id: Int) {
        viewModelScope.launch {
            getPlanetDetailUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading ->
                        _state.update { it.copy(isLoading = true) }

                    is Resource.Success ->
                        _state.update { it.copy(isLoading = false, planet = result.data) }

                    is Resource.Error ->
                        _state.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }
}