package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.leonardomiyagi.kotlinbaseapplication.R
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.DialogUtils
import dagger.android.AndroidInjection

/**
 * Created by lmiyagi on 11/8/17.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
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