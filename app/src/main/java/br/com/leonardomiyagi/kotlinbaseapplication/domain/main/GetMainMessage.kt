package br.com.leonardomiyagi.kotlinbaseapplication.domain.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import io.reactivex.Single

class GetMainMessage constructor(private val repository: Repository) {

    fun execute(): Single<String> {
        return repository.getMessage()
    }
}