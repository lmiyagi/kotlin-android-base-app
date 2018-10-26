package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.com.leonardomiyagi.kotlinbaseapplication.R
import br.com.leonardomiyagi.kotlinbaseapplication.databinding.ActivityMainBinding
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BaseActivity
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.databinding.PlaceholderData
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.DialogUtils
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainContract.View {

    private val presenter: MainContract.Presenter by inject()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun renderMessage(message: String) {
        binding.messageTextView.text = message
    }

    override fun showLoading() {
        binding.placeholders?.data = PlaceholderData.loading(this)
    }

    override fun hideLoading() {
        binding.placeholders?.data = PlaceholderData.hideAll()
    }

    override fun showError(error: Throwable, tryAgainAction: (() -> Unit)?) {
        DialogUtils.showErrorDialog(this, error, tryAgainAction)
    }
}
