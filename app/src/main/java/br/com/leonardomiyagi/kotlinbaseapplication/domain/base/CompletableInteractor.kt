package br.com.leonardomiyagi.kotlinbaseapplication.domain.base

import io.reactivex.Completable

interface CompletableInteractor<in RV : RequestValues> {
    fun execute(requestValues: RV? = null): Completable
}