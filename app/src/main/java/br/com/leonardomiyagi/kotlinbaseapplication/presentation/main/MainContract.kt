package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

/**
 * Created by lmiyagi on 11/8/17.
 */
interface MainContract {

    interface View {
        fun renderMessage(message: String)

    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
    }
}