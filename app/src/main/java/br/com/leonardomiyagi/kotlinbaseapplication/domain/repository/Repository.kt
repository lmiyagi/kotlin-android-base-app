package br.com.leonardomiyagi.kotlinbaseapplication.domain.repository

import io.reactivex.Single

/**
 * Created by lmiyagi on 04/04/18.
 */
interface Repository {

    @Throws(Throwable::class)
    suspend fun getErrorExample(): String
    @Throws(Throwable::class)
    suspend fun getMessage(): String
}