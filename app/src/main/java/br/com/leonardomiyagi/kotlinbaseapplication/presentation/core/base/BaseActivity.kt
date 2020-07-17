package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import br.com.leonardomiyagi.kotlinbaseapplication.R
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.extentions.observeDirectly
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.DialogUtils
import org.koin.androidx.scope.bindScope
import org.koin.androidx.scope.lifecycleScope

/**
 * Created by lmiyagi on 11/8/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract val baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindScope(lifecycleScope)
        setupObservers()
    }

    protected open fun setupObservers() {
        baseViewModel.errorLiveData.observeDirectly(this) {
            showErrorDialog(it)
        }
    }

    protected open fun showErrorDialog(error: Throwable, tryAgainAction: (() -> Unit)? = null) {
        DialogUtils.showErrorDialog(this, error, tryAgainAction)
    }

    protected open fun showDialog(title: String,
                                  message: String,
                                  positiveMessage: String = getString(R.string.global_ok),
                                  positiveAction: (() -> Unit)? = null,
                                  negativeMessage: String? = null,
                                  negativeAction: (() -> Unit)? = null,
                                  cancelAction: (() -> Unit)? = null) {
        DialogUtils.showDialog(this,
                title,
                message,
                positiveMessage,
                negativeMessage,
                positiveAction,
                negativeAction,
                cancelAction)
    }

    protected open fun showDialog(@StringRes title: Int,
                                  @StringRes message: Int,
                                  @StringRes positiveMessage: Int = R.string.global_ok,
                                  positiveAction: (() -> Unit)? = null,
                                  @StringRes negativeMessage: Int? = null,
                                  negativeAction: (() -> Unit)? = null,
                                  cancelAction: (() -> Unit)? = null) {
        showDialog(getString(title),
                getString(message),
                getString(positiveMessage),
                positiveAction,
                negativeMessage?.run { getString(this) },
                negativeAction,
                cancelAction)
    }

    protected fun showToast(message: String,
                            duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    protected fun showToast(@StringRes message: Int,
                            duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}