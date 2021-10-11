package com.endrjudev.data

import com.endrjudev.data.database.GitHubDatabase
import com.endrjudev.data.entity.RepositoryEntity
import com.endrjudev.data.network.NetworkDataSource
import com.endrjudev.domain.Repository
import com.endrjudev.domain.model.RepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class GitHubRepository(
    private val networkDataSource: NetworkDataSource,
    private val database: GitHubDatabase
) : Repository {

    override fun getRepositories(query: String): Flow<List<RepositoryModel>> {
        return flow {
            val response = networkDataSource.getRepositories(query)
                .items
                .map {
                    RepositoryEntity(
                        id = it.id,
                        description = it.description,
                        language = it.language,
                        name = it.name,
                        ownerName = it.owner.login,
                        ownerPicture = it.owner.avatarUrl,
                        score = it.score,
                        size = it.size,
                        cloneUrl = it.cloneUrl,
                        watchersCount = it.watchersCount
                    )
                }

            database.repositoryDao().saveItems(response)

            val value = database.repositoryDao().getItems()
                .map { it.toPresentationModel() }

            emit(value)
        }
    }

    override fun getRepository(itemId: Int): Flow<RepositoryModel> {
        return flow {
            val value = database.repositoryDao().getItem(itemId)
                .toPresentationModel()

            emit(value)
        }
    }

    private fun RepositoryEntity.toPresentationModel() =
        RepositoryModel(
            id = id,
            description = description,
            language = language,
            name = name,
            ownerName = ownerName,
            ownerPicture = ownerPicture,
            score = score,
            size = size,
            cloneUrl = cloneUrl,
            watchersCount = watchersCount
        )
}