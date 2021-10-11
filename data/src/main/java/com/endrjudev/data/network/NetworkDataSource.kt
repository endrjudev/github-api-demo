package com.endrjudev.data.network

import com.endrjudev.data.model.Response
import io.ktor.client.request.*
import io.ktor.http.*

internal class NetworkDataSource {

    suspend fun getRepositories(query: String): Response {
        return KtorClient.httpClient.request {
            method = HttpMethod.Get
            url(SEARCH_REPO_ENDPOINT)
            parameter("q", query)
            parameter("sort", "stars")
            parameter("order", "desc")
        }
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private const val SEARCH_REPO_ENDPOINT = "${BASE_URL}search/repositories"
    }
}