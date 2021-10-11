package com.endrjudev.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endrjudev.domain.model.RepositoryModel
import com.endrjudev.domain.usecase.GetRepositoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class DetailViewModel(
    useCase: GetRepositoryUseCase,
    itemId: Int
) : ViewModel() {

    val state = useCase.invoke(itemId)
        .flowOn(Dispatchers.IO)
        .conflate()
        .map { UiState.Content(it) }
        .onStart { delay(1000) }
        .catch { UiState.Error }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = UiState.Loading
        )

    sealed class UiState {
        object Loading : UiState()
        object Error : UiState()
        data class Content(val repositoryModel: RepositoryModel) : UiState()
    }
}