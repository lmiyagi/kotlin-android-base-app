package br.com.leonardomiyagi.kotlinbaseapplication.data.repository

import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiClient
import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository

/**
 * Created by lmiyagi on 04/04/18.
 */
class DefaultRepository constructor(private val apiClient: ApiClient) : Repository {

    override suspend fun getErrorExample(): String {
        return apiClient.getErrorExample()
    }

    override suspend fun getMessage(): String {
        return apiClient.getMessage()
    }
}