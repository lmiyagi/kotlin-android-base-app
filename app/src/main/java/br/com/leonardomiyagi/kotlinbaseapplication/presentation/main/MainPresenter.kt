package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetMainMessage
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by lmiyagi on 11/8/17.
 */
class MainPresenter @Inject constructor(private val getMainMessage: GetMainMessage)
    : MainContract.Presenter() {

    override fun onViewAttached() {
        getMainMessage()
    }

    private fun getMainMessage() {
        runInteractor(getMainMessage.execute())
                .doOnSubscribe {
                    view?.showLoading()
                }
                .doFinally {
                    view?.hideLoading()
                }
                .subscribeBy(
                        onSuccess = {
                            view?.renderMessage(it)
                        },
                        onError = {
                            view?.showError(it, this::getMainMessage)
                        }
                )

    }
}