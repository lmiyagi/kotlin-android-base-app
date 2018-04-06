package br.com.leonardomiyagi.kotlinbaseapplication.domain.base

import io.reactivex.Observable

/**
 * Created by lmiyagi on 06/04/18.
 */
interface ObservableInteractor<in RV : RequestValues, T> {
    fun execute(requestValues: RV? = null): Observable<T>
}