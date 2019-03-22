package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import br.com.leonardomiyagi.kotlinbaseapplication.R
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.ACTIVITY_SCOPE
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.DialogUtils
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.createScope

/**
 * Created by lmiyagi on 11/8/17.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindScope(createScope(ACTIVITY_SCOPE))
    }

    fun showErrorDialog(error: Throwable, tryAgainAction: (() -> Unit)? = null) {
        DialogUtils.showErrorDialog(this, error, tryAgainAction)
    }

    fun showDialog(title: String,
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

    fun showDialog(@StringRes title: Int,
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

    fun showToast(message: String,
                  duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    fun showToast(@StringRes message: Int,
                  duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}