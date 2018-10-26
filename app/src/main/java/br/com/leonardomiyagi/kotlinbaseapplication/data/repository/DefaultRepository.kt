package br.com.leonardomiyagi.kotlinbaseapplication.data.repository

import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiClient
import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import io.reactivex.Single

/**
 * Created by lmiyagi on 04/04/18.
 */
class DefaultRepository constructor(private val apiClient: ApiClient)
    : Repository {

    override fun getMessage(): Single<String> {
        return apiClient.getMessage()
    }
}