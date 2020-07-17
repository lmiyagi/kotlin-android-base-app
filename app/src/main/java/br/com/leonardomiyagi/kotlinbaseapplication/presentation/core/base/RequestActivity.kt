package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import androidx.annotation.StringRes
import br.com.leonardomiyagi.kotlinbaseapplication.R
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.exception.RequestException
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

    override fun showErrorDialog(error: Throwable, tryAgainAction: (() -> Unit)?) {
        if (error is RequestException) {
            when {
                error.isNetworkError() -> {
                    showRequestError(R.string.error_network)
                }
                error.isUnexpectedError() -> {
                    showRequestError(R.string.error_unexpected)
                }
                error.isTimeOutException() -> {
                    showRequestError(R.string.error_timeout)
                }
                error.isNotFoundError() -> {
                    showRequestError(R.string.error_not_found)
                }
                error.isServerInternalError() -> {
                    showRequestError(R.string.error_server_internal)
                }
            }
        } else {
            super.showErrorDialog(error, tryAgainAction)
        }
    }

    private fun showRequestError(@StringRes message: Int) {
        super.showDialog(R.string.global_op_failure,
                message,
                R.string.global_ok,
                null,
                null,
                null,
                null)
    }
}