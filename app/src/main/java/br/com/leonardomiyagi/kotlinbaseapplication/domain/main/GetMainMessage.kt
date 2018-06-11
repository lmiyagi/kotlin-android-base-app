package br.com.leonardomiyagi.kotlinbaseapplication.domain.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class GetMainMessage @Inject constructor(private val repository: Repository) {

    fun execute(): Single<String> {
        return repository.getMessage()
    }
}