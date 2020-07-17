package br.com.leonardomiyagi.kotlinbaseapplication.domain.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository

class GetMainMessage constructor(private val repository: Repository) {

    suspend fun execute(): String {
        return repository.getMessage()
    }
}