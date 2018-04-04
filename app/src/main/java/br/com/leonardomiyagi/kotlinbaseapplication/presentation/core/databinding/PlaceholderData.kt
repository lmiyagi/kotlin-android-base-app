package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.databinding

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import br.com.leonardomiyagi.kotlinbaseapplication.R

/**
 * Created by lmiyagi on 04/04/18.
 */
class PlaceholderData private constructor(private val context: Context? = null,
                                          val show: Boolean = true,
                                          val isLoading: Boolean = false,
                                          @DrawableRes val icon: Int? = null,
                                          val message: String? = null,
                                          val tryAgain: Boolean = false,
                                          val tryAgainAction: (() -> Unit)? = null) {

    fun executeTryAgain() {
        tryAgainAction?.invoke()
    }

    fun getIconDrawable(): Drawable? {
        if (icon == null || context == null) return null
        return ContextCompat.getDrawable(context, icon)
    }

    companion object {

        fun loading(context: Context) =
                PlaceholderData(isLoading = true,
                        message = context.getString(R.string.global_loading))

        fun error(context: Context, error: String) =
                PlaceholderData(message = error)

        fun error(context: Context, @StringRes error: Int) =
                PlaceholderData(message = context.getString(error))

        fun error(context: Context, error: String, @DrawableRes icon: Int) =
                PlaceholderData(context = context,
                        icon = icon,
                        message = error)

        fun error(context: Context, @StringRes error: Int, @DrawableRes icon: Int) =
                PlaceholderData(context = context,
                        icon = icon,
                        message = context.getString(error))

        fun unexpectedError(context: Context) =
                PlaceholderData(message = context.getString(R.string.global_unexpected_error))

        fun unexpectedError(context: Context, tryAgainAction: () -> Unit) =
                PlaceholderData(message = context.getString(R.string.global_unexpected_error),
                        tryAgain = true,
                        tryAgainAction = tryAgainAction)

        fun error(context: Context, message: String, tryAgainAction: () -> Unit) =
                PlaceholderData(message = message,
                        tryAgain = true,
                        tryAgainAction = tryAgainAction)

        fun error(context: Context, @StringRes message: Int, tryAgainAction: () -> Unit) =
                PlaceholderData(message = context.getString(message),
                        tryAgain = true,
                        tryAgainAction = tryAgainAction)

        fun error(context: Context, message: String, @DrawableRes icon: Int, tryAgainAction: () -> Unit) =
                PlaceholderData(message = message,
                        icon = icon,
                        tryAgain = true,
                        tryAgainAction = tryAgainAction)

        fun error(context: Context, @StringRes message: Int, @DrawableRes icon: Int, tryAgainAction: () -> Unit) =
                PlaceholderData(message = context.getString(message),
                        icon = icon,
                        tryAgain = true,
                        tryAgainAction = tryAgainAction)

        fun hideAll() = PlaceholderData(show = false)
    }
}