package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

/**
 * Created by lmiyagi on 06/04/18.
 */
interface BaseContract {

    interface View {
        fun closeView()
    }

    interface Presenter<in View : BaseContract.View> {
        fun attachView(view: View)
        fun detachView()
    }
}