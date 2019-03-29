package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BasePresenter<View : BaseContract.View> : KoinComponent {

    /**
     * The [View] class that is attached on [attachView]
     */
    protected var view: View? = null

    /**
     * A disposable "bag" that gets disposed on [detachView]
     *
     * @see [Disposable]
     */
    private val disposables = CompositeDisposable()

    /**
     * Schedulers to be used on the interactors
     *
     * @see [Scheduler]
     */
    protected val schedulers by inject<SchedulerProvider>()

    /**
     * Attaches the [View] and runs [onViewAttached]
     */
    fun attachView(view: View) {
        this.view = view
        onViewAttached()
    }

    /**
     * Runs [onViewDetached], detaches the [View], and disposes all [Disposable]s collected from the
     * [runInteractor] methods
     */
    fun detachView() {
        onViewDetached()
        this.view = null
        disposables.clear()
    }

    /**
     * Runs just after the [View] is attached. You don't have to implement super.onViewAttached()
     * method if you are implementing this method
     */
    open fun onViewAttached() {}

    /**
     * Runs just before the [View] is detached. You don't have to implement super.onViewAttached()
     * method if you are implementing this method
     */
    open fun onViewDetached() {}

    /**
     * Runs an [Observable], subscribing on [SchedulerProvider.io] and observing on
     * [SchedulerProvider.main] by default.
     *
     * The [Disposable] will be added on the [BasePresenter]'s [CompositeDisposable], and will be
     * disposed on [onViewDetached]
     *
     * @param interactor an [Observable]<[T]> to be subscribed
     * @param subscribeOn the [Scheduler] to perform subscription actions on (default: io)
     * @param observeOn the [Scheduler] to notify Observers on (default: main)
     *
     * @return the same Observable from the params, but with [Scheduler]s and
     * [Disposable] handled
     */
    fun <T : Any> runInteractor(interactor: Observable<T>,
                                subscribeOn: Scheduler = schedulers.io(),
                                observeOn: Scheduler = schedulers.main()): Observable<T> {
        return interactor.doOnSubscribe { disposables.add(it) }
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }

    /**
     * Runs an [Single], subscribing on [SchedulerProvider.io] and observing on
     * [SchedulerProvider.main] by default.
     *
     * The [Disposable] will be added on the [BasePresenter]'s [CompositeDisposable], and will be
     * disposed on [onViewDetached]
     *
     * @param interactor a [Single]<[T]> to be subscribed
     * @param subscribeOn the [Scheduler] to perform subscription actions on (default: io)
     * @param observeOn the [Scheduler] to notify Observers on (default: main)
     *
     * @return the same [Single]<[T]> from the params, but with [Scheduler]s and
     * [Disposable] handled
     */
    fun <T : Any> runInteractor(interactor: Single<T>,
                                subscribeOn: Scheduler = schedulers.io(),
                                observeOn: Scheduler = schedulers.main()): Single<T> {
        return interactor.doOnSubscribe { disposables.add(it) }
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }

    /**
     * Runs an [Completable], subscribing on [SchedulerProvider.io] and observing on
     * [SchedulerProvider.main] by default.
     *
     * The [Disposable] will be added on the [BasePresenter]'s [CompositeDisposable], and will be
     * disposed on [onViewDetached]
     *
     * @param interactor a [Completable] to be subscribed
     * @param subscribeOn the [Scheduler] to perform subscription actions on (default: io)
     * @param observeOn the [Scheduler] to notify Observers on (default: main)
     *
     * @return the same Completable from the params, but with [Scheduler]s and [Disposable] handled
     */
    fun runInteractor(interactor: Completable,
                      subscribeOn: Scheduler = schedulers.io(),
                      observeOn: Scheduler = schedulers.main()): Completable {
        return interactor.doOnSubscribe { disposables.add(it) }
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }
}