package br.com.leonardomiyagi.kotlinbaseapplication.data.repository

import br.com.leonardomiyagi.kotlinbaseapplication.data.api.exception.RequestException
import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import io.reactivex.Single
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by lmiyagi on 04/04/18.
 */
class DefaultRepository @Inject constructor()
    : Repository {

    override fun getMessage(): Single<String> {
        return Single.just("This message is coming from repository!").delay(3, TimeUnit.SECONDS)
    }
}