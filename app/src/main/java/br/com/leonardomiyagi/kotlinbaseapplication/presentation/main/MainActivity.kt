package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import android.os.Bundle
import br.com.leonardomiyagi.kotlinbaseapplication.R
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.RequestActivity
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.RequestViewModel
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.extentions.observeDirectly
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : RequestActivity() {

    override val requestViewModel: RequestViewModel get() = viewModel

    private val viewModel by lifecycleScope.viewModel<MainViewModel>(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupClickListeners()
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.messageLiveData.observeDirectly(this, ::renderMessage)
    }

    private fun setupClickListeners() {
        simulateErrorButton.setOnClickListener {
            viewModel.onSimulateErrorClicked()
        }
    }

    private fun renderMessage(message: String) {
        messageTextView.text = message
    }
}
