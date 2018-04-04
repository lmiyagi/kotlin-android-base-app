package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetMainMessage
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BasePresenter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by lmiyagi on 11/8/17.
 */
class MainPresenter @Inject constructor(private val getMainMessage: GetMainMessage)
    : BasePresenter(), MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
        getMainMessage()
    }

    override fun detachView() {
        this.view = null
    }

    private fun getMainMessage() {
        interactorHelper.execute(getMainMessage)
                .doOnSubscribe {
                    view?.showLoading()
                }
                .doAfterTerminate {
                    view?.hideLoading()
                }
                .subscribeBy(
                        onSuccess = {
                            view?.renderMessage(it)
                        },
                        onError = {
                            // todo implement errors
                        }
                )
    }
}