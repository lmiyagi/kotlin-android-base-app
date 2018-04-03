package br.com.leonardomiyagi.kotlinbaseapplication.domain.base

import io.reactivex.Single

interface SingleInteractor<in RV : RequestValues, T> {
    fun execute(requestValues: RV? = null): Single<T>
}