package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetMainMessage
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BasePresenter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by lmiyagi on 11/8/17.
 */
class MainPresenter @Inject constructor(private val getMainMessage: GetMainMessage)
    : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun attachView(view: MainContract.View) {
        super.attachView(view)
        getMainMessage()
    }

    private fun getMainMessage() {
        val getMainMessageDisposable = interactorHelper.execute(getMainMessage)
                .doOnSubscribe {
                    view?.showLoading()
                }
                .subscribeBy(
                        onSuccess = {
                            view?.hideLoading()
                            view?.renderMessage(it)
                        },
                        onError = {
                            view?.showError(it, this::getMainMessage)
                        }
                )
        disposables.add(getMainMessageDisposable)
    }
}