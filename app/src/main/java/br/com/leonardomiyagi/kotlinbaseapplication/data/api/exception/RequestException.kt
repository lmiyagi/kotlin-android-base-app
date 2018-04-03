package br.com.leonardomiyagi.kotlinbaseapplication.data.api.exception

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
            return RequestException(null, null, ErrorType.TIMEOUT, exception)
        }

        fun unexpectedError(throwable: Throwable): RequestException {
            throwable.printStackTrace()
            return RequestException(null, throwable.message, ErrorType.UNEXPECTED, throwable)
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
        return isHttpError() && HttpError.getErrorForCode(errorCode) == HttpError.UNAUTHORIZED
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }

    fun getThrowable(): Throwable? {
        return throwable
    }

    fun isTimeOutException(): Boolean {
        return errorType == ErrorType.TIMEOUT || HttpError.getErrorForCode(errorCode) == HttpError.TIMEOUT
    }

    private enum class ErrorType {
        HTTP, NETWORK, UNEXPECTED, TIMEOUT
    }

    enum class HttpError {
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        TIMEOUT,
        UNPROCESSABLE_ENTITY,
        INTERNAL_SERVER_ERROR,
        UNEXPECTED_ERROR;

        companion object {
            fun getErrorForCode(errorCode: Int?): HttpError {
                errorCode?.let {
                    return when (it) {
                        400 -> BAD_REQUEST
                        401 -> UNAUTHORIZED
                        403 -> FORBIDDEN
                        404 -> NOT_FOUND
                        408 -> TIMEOUT
                        422 -> UNPROCESSABLE_ENTITY
                        500 -> INTERNAL_SERVER_ERROR
                        else -> UNEXPECTED_ERROR
                    }
                }
                return UNEXPECTED_ERROR
            }
        }
    }
}