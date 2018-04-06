package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.RequestView

/**
 * Created by lmiyagi on 11/8/17.
 */
interface MainContract {

    interface View : RequestView {
        fun renderMessage(message: String)

    }

    interface Presenter {
        fun onViewCreated()
        fun onViewDestroyed()
    }
}