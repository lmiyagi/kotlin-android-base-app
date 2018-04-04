package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

/**
 * Created by lmiyagi on 04/04/18.
 */
interface RequestView : LoadableView {

    fun showError(error: Throwable, tryAgainAction: (() -> Unit)? = null)
}