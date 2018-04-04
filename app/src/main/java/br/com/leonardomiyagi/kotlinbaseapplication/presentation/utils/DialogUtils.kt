package br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils

import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import br.com.leonardomiyagi.kotlinbaseapplication.R

/**
 * Created by lmiyagi on 04/04/18.
 */
object DialogUtils {

    fun showOkDialog(context: Context,
                     title: String,
                     message: String,
                     okAction: (() -> Unit)? = null,
                     dismissAction: (() -> Unit)? = null) {
        showDialog(context = context,
                title = title,
                message = message,
                positiveMessage = context.getString(R.string.global_ok),
                positiveAction = okAction,
                dismissAction = dismissAction)
    }

    fun showOkDialog(context: Context,
                     @StringRes title: Int,
                     @StringRes message: Int,
                     okAction: (() -> Unit)? = null,
                     dismissAction: (() -> Unit)? = null) {
        showDialog(context = context,
                title = context.getString(title),
                message = context.getString(message),
                positiveMessage = context.getString(R.string.global_ok),
                positiveAction = okAction,
                dismissAction = dismissAction)
    }

    fun showOkCancelDialog(context: Context,
                           title: String,
                           message: String,
                           okAction: (() -> Unit)? = null,
                           cancelAction: (() -> Unit)? = null,
                           dismissAction: (() -> Unit)? = null) {
        showDialog(context = context,
                title = title,
                message = message,
                positiveMessage = context.getString(R.string.global_ok),
                positiveAction = okAction,
                negativeMessage = context.getString(R.string.global_cancel),
                negativeAction = cancelAction,
                dismissAction = dismissAction)
    }

    fun showOkCancelDialog(context: Context,
                           @StringRes title: Int,
                           @StringRes message: Int,
                           okAction: (() -> Unit)? = null,
                           cancelAction: (() -> Unit)? = null,
                           dismissAction: (() -> Unit)? = null) {
        showDialog(context = context,
                title = context.getString(title),
                message = context.getString(message),
                positiveMessage = context.getString(R.string.global_ok),
                positiveAction = okAction,
                negativeMessage = context.getString(R.string.global_cancel),
                negativeAction = cancelAction,
                dismissAction = dismissAction)
    }

    fun showDialog(context: Context,
                   title: String,
                   message: String,
                   positiveMessage: String,
                   negativeMessage: String? = null,
                   positiveAction: (() -> Unit)? = null,
                   negativeAction: (() -> Unit)? = null,
                   dismissAction: (() -> Unit)? = null) {

        val builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(positiveMessage, { _, _ -> positiveAction?.invoke() })
            negativeMessage?.let {
                setNegativeButton(it, { _, _ -> negativeAction?.invoke() })
            }
            setOnDismissListener { dismissAction?.invoke() }
        }
        builder.create().show()
    }
}