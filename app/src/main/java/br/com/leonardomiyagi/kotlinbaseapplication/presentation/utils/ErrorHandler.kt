package br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.leonardomiyagi.kotlinbaseapplication.R
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.exception.RequestException
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.databinding.PlaceholderData


/**
 * Created by lmiyagi on 04/04/18.
 */
object ErrorHandler {

    fun handleError(context: Context, throwable: Throwable, tryAgainAction: (() -> Unit)? = null): PlaceholderData {
        throwable.printStackTrace()
        return if (throwable is RequestException) {
            handleRequestException(context, throwable, tryAgainAction)
        } else {
            if (throwable.message == null) {
                unexpectedErrorData(context, tryAgainAction)
            } else {
                PlaceholderData.error(context = context,
                        message = throwable.message,
                        tryAgainAction = tryAgainAction)
            }
        }
    }

    private fun handleRequestException(context: Context, exception: RequestException, tryAgainAction: (() -> Unit)? = null): PlaceholderData {
        return when {
            exception.isTimeOutException() -> timeoutErrorData(context, tryAgainAction)
            exception.isNetworkError() -> httpErrorData(context, R.string.error_network, R.drawable.ic_no_connection, tryAgainAction)
            exception.isHttpError() -> when (RequestException.HttpError.getErrorForCode(exception.errorCode)) {
                RequestException.HttpError.NOT_FOUND -> httpErrorData(context, R.string.error_not_found, R.drawable.ic_find, tryAgainAction)
                RequestException.HttpError.TIMEOUT -> timeoutErrorData(context, tryAgainAction)
                RequestException.HttpError.INTERNAL_SERVER_ERROR -> httpErrorData(context, R.string.error_server_internal, R.drawable.ic_error_outline, tryAgainAction)
                else -> unexpectedErrorData(context, tryAgainAction)
            }
            else -> unexpectedErrorData(context, tryAgainAction)
        }
    }

    private fun httpErrorData(context: Context, @StringRes message: Int, @DrawableRes icon: Int? = null, tryAgainAction: (() -> Unit)? = null): PlaceholderData {
        return PlaceholderData.error(context, message, icon, tryAgainAction)
    }

    private fun timeoutErrorData(context: Context, tryAgainAction: (() -> Unit)? = null): PlaceholderData {
        return PlaceholderData.error(context, R.string.error_timeout, R.drawable.ic_timeout, tryAgainAction)
    }

    private fun unexpectedErrorData(context: Context, tryAgainAction: (() -> Unit)? = null): PlaceholderData {
        return PlaceholderData.unexpectedError(context, tryAgainAction)
    }
}