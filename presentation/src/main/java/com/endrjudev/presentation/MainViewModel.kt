package com.endrjudev.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endrjudev.domain.model.RepositoryModel
import com.endrjudev.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MainViewModel(useCase: GetRepositoriesUseCase) : ViewModel() {

    val state = useCase.invoke(QUERY)
        .flowOn(Dispatchers.IO)
        .conflate()
        .map { repositories -> UiState.Content(repositories) }
        .onStart { delay(1000) }
        .catch { UiState.Error }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = UiState.Loading
        )

    sealed class UiState {
        object Loading : UiState()
        object Error : UiState()
        data class Content(val repositories: List<RepositoryModel>) : UiState()
    }

    companion object {
        private const val QUERY = "kotlin"
    }
}