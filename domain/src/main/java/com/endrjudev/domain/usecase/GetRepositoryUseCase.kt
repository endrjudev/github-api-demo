package com.endrjudev.domain.usecase

import com.endrjudev.domain.Repository
import com.endrjudev.domain.model.RepositoryModel
import kotlinx.coroutines.flow.Flow

class GetRepositoryUseCase(
    private val repository: Repository
) {
    operator fun invoke(itemId: Int): Flow<RepositoryModel> = repository.getRepository(itemId)
}