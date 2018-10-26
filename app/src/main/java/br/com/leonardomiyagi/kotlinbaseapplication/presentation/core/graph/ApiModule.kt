package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.BuildConfig
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiClient
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiService
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.API_BASE_STAGING_URL
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.API_BASE_URL
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.DEBUG
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.D_API_BASE_URL
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
                BuildConfig.BUILD_TYPE.equals(DEBUG, true)) {
            API_BASE_STAGING_URL
        } else {
            API_BASE_URL
        }
    }

    single<HttpLoggingInterceptor> {
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

    single<ApiService> { (retrofit: Retrofit) -> retrofit.create(ApiService::class.java) }

    single { (apiService: ApiService) -> ApiClient(apiService) }
}