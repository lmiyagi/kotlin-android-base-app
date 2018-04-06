package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.Navigator
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.InteractorHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BasePresenter {

    protected val disposables = CompositeDisposable()

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var interactorHelper: InteractorHelper

    protected fun onDetachView() {
        disposables.clear()
    }
}