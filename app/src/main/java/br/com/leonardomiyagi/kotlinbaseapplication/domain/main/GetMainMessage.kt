package br.com.leonardomiyagi.kotlinbaseapplication.domain.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.base.RequestValues
import br.com.leonardomiyagi.kotlinbaseapplication.domain.base.SingleInteractor
import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class GetMainMessage @Inject constructor(private val repository: Repository)
    : SingleInteractor<RequestValues, String> {

    override fun execute(requestValues: RequestValues?): Single<String> {
        return repository.getMessage()
    }
}