package br.com.leonardomiyagi.kotlinbaseapplication.domain.repository

import io.reactivex.Single

/**
 * Created by lmiyagi on 04/04/18.
 */
interface Repository {

    suspend fun getExample(): String
    suspend fun getMessage(): String
}