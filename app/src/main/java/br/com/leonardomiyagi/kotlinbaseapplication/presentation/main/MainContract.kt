package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.LoadableView

/**
 * Created by lmiyagi on 11/8/17.
 */
interface MainContract {

    interface View : LoadableView {
        fun renderMessage(message: String)

    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
    }
}