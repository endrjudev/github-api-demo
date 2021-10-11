package com.endrjudev.domain.usecase

import com.endrjudev.domain.Repository
import com.endrjudev.domain.model.RepositoryModel
import kotlinx.coroutines.flow.Flow

class GetRepositoriesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<List<RepositoryModel>> = repository.getRepositories(query)
}