package br.com.leonardomiyagi.kotlinbaseapplication.data.api

import br.com.leonardomiyagi.kotlinbaseapplication.data.api.exception.NullResponseException
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.exception.RequestException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by lmiyagi on 11/8/17.
 */
class ApiClient(private val apiService: ApiService) {

    suspend fun getMessage(): String {
        // TODO remove this example
        return coroutineScope {
            delay(3000L)
            "This message is coming from the API!"
        }
    }

    suspend fun getErrorExample(): String {
        // TODO remove this error example
        return coroutineScope {
            delay(3000L)
            throw   RequestException.httpError(500, "Internal server error")
        }
    }

    private suspend fun <T> makeRequest(response: suspend () -> Response<T>): T {
        return coroutineScope {
            try {
                response().run {
                    if (isSuccessful) {
                        body() ?: throw NullResponseException()
                    } else {
                        throw RequestException.httpError(code(), message())
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                throw when (exception) {
                    is NullResponseException -> exception
                    is RequestException -> exception
                    is SocketTimeoutException -> RequestException.timeoutError(exception)
                    is IOException -> RequestException.networkError(exception)
                    else -> RequestException.unexpectedError(exception)
                }
            }
        }
    }
}