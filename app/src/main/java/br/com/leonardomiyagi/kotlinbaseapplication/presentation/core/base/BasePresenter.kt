package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.Navigator
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.InteractorHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BasePresenter<View : BaseContract.View> : BaseContract.Presenter<View> {

    protected var view: View? = null

    protected val disposables = CompositeDisposable()

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var interactorHelper: InteractorHelper

    override fun attachView(view: View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposables.clear()
    }
}