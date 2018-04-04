package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
                   dismissAction: (() -> Unit)? = null) {
        DialogUtils.showDialog(this,
                title,
                message,
                positiveMessage,
                negativeMessage,
                positiveAction,
                negativeAction,
                dismissAction)
    }
}