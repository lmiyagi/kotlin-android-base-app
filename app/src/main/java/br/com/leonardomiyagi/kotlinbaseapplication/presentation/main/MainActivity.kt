package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import br.com.leonardomiyagi.kotlinbaseapplication.R
import br.com.leonardomiyagi.kotlinbaseapplication.databinding.ActivityMainBinding
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BaseActivity
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.databinding.PlaceholderData
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
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
        binding.placeholders?.data = PlaceholderData.error(this, error.message, tryAgainAction)
    }
}
