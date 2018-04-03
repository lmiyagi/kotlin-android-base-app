package br.com.leonardomiyagi.kotlinbaseapplication.data.api

import br.com.leonardomiyagi.kotlinbaseapplication.data.api.exception.RequestException
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by lmiyagi on 11/8/17.
 */
class ApiClient(val apiService: ApiService) {

    private fun <T> verifyResponseException(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.doOnSuccess({ response ->
                if (!response.isSuccessful) {
                    throw RequestException.httpError(response.code(), response.message())
                }
            })
        }
    }

    private fun <T> verifyRequestException(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.onErrorResumeNext({ t ->
                when (t) {
                    is RequestException -> Single.error(t)
                    is SocketTimeoutException -> Single.error(RequestException.timeoutError(t))
                    is IOException -> Single.error(RequestException.networkError(t))
                    else -> Single.error(RequestException.unexpectedError(t))
                }
            })
        }
    }

    private fun <T> unwrap(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { upstream ->
            upstream.map(Response<T>::body)
        }
    }
}