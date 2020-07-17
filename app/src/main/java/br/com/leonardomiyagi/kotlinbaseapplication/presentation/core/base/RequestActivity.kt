package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import br.com.leonardomiyagi.kotlinbaseapplication.presentation.extentions.observeDirectly
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.extentions.setVisibility
import kotlinx.android.synthetic.main.view_loading.*

abstract class RequestActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = requestViewModel

    abstract val requestViewModel: RequestViewModel

    override fun setupObservers() {
        super.setupObservers()
        requestViewModel.loadingLiveData.observeDirectly(this, ::toggleLoading)
    }

    protected fun toggleLoading(isLoading: Boolean) {
        loadingContainer.setVisibility(isLoading)
    }
}