package br.com.leonardomiyagi.kotlinbaseapplication.domain.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository

class GetErrorExample(private val repository: Repository) {

    suspend fun execute(): String {
        return repository.getErrorExample()
    }
}