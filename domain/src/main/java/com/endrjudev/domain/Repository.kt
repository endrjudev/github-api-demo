package com.endrjudev.domain

import com.endrjudev.domain.model.RepositoryModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getRepositories(query: String): Flow<List<RepositoryModel>>
    fun getRepository(itemId: Int) : Flow<RepositoryModel>
}