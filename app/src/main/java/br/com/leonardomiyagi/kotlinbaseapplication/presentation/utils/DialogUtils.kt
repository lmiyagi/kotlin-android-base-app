package br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import br.com.leonardomiyagi.kotlinbaseapplication.R

/**
 * Created by lmiyagi on 04/04/18.
 */
object DialogUtils {

    fun showErrorDialog(context: Context, error: Throwable, tryAgainAction: (() -> Unit)? = null) {
        showDialog(context,
                context.getString(R.string.global_op_failure),
                error.message ?: context.getString(R.string.error_unexpected),
                if (tryAgainAction == null) {
                    context.getString(R.string.global_ok)
                } else {
                    context.getString(R.string.global_try_again)
                },
                null,
                tryAgainAction,
                null,
                null)
    }

    fun showOkDialog(context: Context,
                     title: String,
                     message: String,
                     okAction: (() -> Unit)? = null,
                     cancelAction: (() -> Unit)? = null) {
        showDialog(context = context,
                title = title,
                message = message,
                positiveMessage = context.getString(R.string.global_ok),
                positiveAction = okAction,
                cancelAction = cancelAction)
    }

    fun showOkDialog(context: Context,
                     @StringRes title: Int,
                     @StringRes message: Int,
                     okAction: (() -> Unit)? = null,
                     cancelAction: (() -> Unit)? = null) {
        showDialog(context = context,
                title = context.getString(title),
                message = context.getString(message),
                positiveMessage = context.getString(R.string.global_ok),
                positiveAction = okAction,
                cancelAction = cancelAction)
    }

    fun showOkCancelDialog(context: Context,
                           title: String,
                           message: String,
                           okAction: (() -> Unit)? = null,
                           cancelAction: (() -> Unit)? = null,
                           dialogCancelAction: (() -> Unit)? = null) {
        showDialog(context = context,
                title = title,
                message = message,
                positiveMessage = context.getString(R.string.global_ok),
                positiveAction = okAction,
                negativeMessage = context.getString(R.string.global_cancel),
                negativeAction = cancelAction,
                cancelAction = dialogCancelAction)
    }

    fun showOkCancelDialog(context: Context,
                           @StringRes title: Int,
                           @StringRes message: Int,
                           okAction: (() -> Unit)? = null,
                           cancelAction: (() -> Unit)? = null,
                           dialogCancelAction: (() -> Unit)? = null) {
        showDialog(context = context,
                title = context.getString(title),
                message = context.getString(message),
                positiveMessage = context.getString(R.string.global_ok),
                positiveAction = okAction,
                negativeMessage = context.getString(R.string.global_cancel),
                negativeAction = cancelAction,
                cancelAction = dialogCancelAction)
    }

    fun showDialog(context: Context,
                   title: String,
                   message: String,
                   positiveMessage: String,
                   negativeMessage: String? = null,
                   positiveAction: (() -> Unit)? = null,
                   negativeAction: (() -> Unit)? = null,
                   cancelAction: (() -> Unit)? = null) {

        val builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(positiveMessage, { _, _ -> positiveAction?.invoke() })
            negativeMessage?.let {
                setNegativeButton(it, { _, _ -> negativeAction?.invoke() })
            }
            setOnCancelListener { cancelAction?.invoke() }
        }
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }
}