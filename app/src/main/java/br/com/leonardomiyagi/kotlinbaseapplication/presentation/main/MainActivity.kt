package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import android.os.Bundle
import br.com.leonardomiyagi.kotlinbaseapplication.R
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BaseActivity
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.DialogUtils
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.setVisibility
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_loading.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainContract.View {

    private val presenter: MainContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun renderMessage(message: String) {
        messageTextView.text = message
    }

    override fun showLoading() {
        loadingContainer.setVisibility(true)
    }

    override fun hideLoading() {
        loadingContainer.setVisibility(false)
    }

    override fun showError(error: Throwable, tryAgainAction: (() -> Unit)?) {
        DialogUtils.showErrorDialog(this, error, tryAgainAction)
    }
}
