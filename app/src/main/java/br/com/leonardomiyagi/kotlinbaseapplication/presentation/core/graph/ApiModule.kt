package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.BuildConfig
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiClient
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiService
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by lmiyagi on 11/8/17.
 */
val apiModule = module {

    single(name = D_API_BASE_URL) {
        if (BuildConfig.BUILD_TYPE.equals(DEBUG, true) ||
                BuildConfig.BUILD_TYPE.equals(STAGING, true)) {
            API_BASE_STAGING_URL
        } else {
            API_BASE_URL
        }
    }

    single<Interceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
                .addNetworkInterceptor(get())
                .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
                .baseUrl(get<String>(name = D_API_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get())
                .build()
    }

    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }

    single { ApiClient(get()) }
}