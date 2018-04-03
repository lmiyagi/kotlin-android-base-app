package br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils

import br.com.leonardomiyagi.kotlinbaseapplication.domain.base.CompletableInteractor
import br.com.leonardomiyagi.kotlinbaseapplication.domain.base.RequestValues
import br.com.leonardomiyagi.kotlinbaseapplication.domain.base.SingleInteractor
import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class InteractorHelper @Inject constructor(private val schedulers: SchedulerProvider) {

    fun <T> execute(interactor: SingleInteractor<RequestValues, T>,
                    requestValues: RequestValues? = null,
                    subscribeOn: Scheduler = schedulers.io(),
                    observeOn: Scheduler = schedulers.main()): Single<T> {
        return interactor.execute(requestValues)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }

    fun execute(interactor: CompletableInteractor<RequestValues>,
                requestValues: RequestValues? = null,
                subscribeOn: Scheduler = schedulers.io(),
                observeOn: Scheduler = schedulers.main()): Completable {
        return interactor.execute(requestValues)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }
}