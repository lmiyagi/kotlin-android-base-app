package br.com.leonardomiyagi.kotlinbaseapplication.data.utils

import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by lmiyagi on 11/8/17.
 */
class RequestException private constructor(private val errorCode: Int?,
                                           private val errorMessage: String?,
                                           private val errorType: ErrorType,
                                           private val throwable: Throwable?) : Exception() {

    companion object {
        fun httpError(errorCode: Int, errorMessage: String?): RequestException {
            return RequestException(errorCode, errorMessage, ErrorType.HTTP, null)
        }

        fun networkError(exception: IOException): RequestException {
            return RequestException(null, null, ErrorType.NETWORK, exception)
        }

        fun timeoutError(exception: SocketTimeoutException): RequestException {
            return RequestException(null, "Tempo limite de requisição excedido.", ErrorType.TIMEOUT, exception)
        }

        fun unexpectedError(throwable: Throwable): RequestException {
            throwable.printStackTrace()
            return RequestException(null, throwable.message ?: "Ocorreu um erro inesperado.", ErrorType.UNEXPECTED, throwable)
        }
    }

    fun isHttpError(): Boolean {
        return errorType == ErrorType.HTTP
    }

    fun isNetworkError(): Boolean {
        return errorType == ErrorType.NETWORK
    }

    fun isUnexpectedError(): Boolean {
        return errorType == ErrorType.UNEXPECTED
    }

    fun isUnauthorizedError(): Boolean {
        return isHttpError() && getHtppError() == HttpError.UNAUTHORIZED
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }

    fun getThrowable(): Throwable? {
        return throwable
    }

    fun isTimeOutException(): Boolean {
        return errorType == ErrorType.TIMEOUT || getHtppError() == HttpError.TIMEOUT
    }

    private enum class ErrorType {
        HTTP, NETWORK, UNEXPECTED, TIMEOUT
    }

    fun getHtppError(): HttpError {
        return when (errorCode) {
            400 -> HttpError.BAD_REQUEST
            401 -> HttpError.UNAUTHORIZED
            403 -> HttpError.FORBIDDEN
            404 -> HttpError.NOT_FOUND
            408 -> HttpError.TIMEOUT
            422 -> HttpError.UNPROCESSABLE_ENTITY
            500 -> HttpError.INTERNAL_SERVER_ERROR
            else -> HttpError.UNEXPECTED_ERROR
        }
    }

    enum class HttpError {
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        TIMEOUT,
        UNPROCESSABLE_ENTITY,
        INTERNAL_SERVER_ERROR,
        UNEXPECTED_ERROR
    }
}