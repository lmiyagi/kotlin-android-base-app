package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiClient
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiService
import br.com.leonardomiyagi.kotlinbaseapplication.data.repository.DefaultRepository
import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetErrorExample
import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetMainMessage
import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.main.MainActivity
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.main.MainViewModel
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by lmiyagi on 11/8/17.
 */
val appModule = module {

    single<Repository> { DefaultRepository(get()) }
}

val apiModule = module {

    single(named(D_API_BASE_URL)) {
        if (BuildConfig.BUILD_TYPE.equals(DEBUG, true) ||
                BuildConfig.BUILD_TYPE.equals(STAGING, true)) {
            API_BASE_STAGING_URL
        } else {
            API_BASE_URL
        }
    }

    single<Interceptor>(named(D_LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        OkHttpClient.Builder()
                .addNetworkInterceptor(get<Interceptor>(named(D_LOGGING_INTERCEPTOR)))
                .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
                .baseUrl(get<String>(named(D_API_BASE_URL)))
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
    }

    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }

    single { ApiClient(get()) }
}

val mainModule = module {

    scope(named<MainActivity>()) {
        scoped { GetMainMessage(get()) }
        scoped { GetErrorExample(get()) }
        viewModel { MainViewModel(get(), get()) }
    }
}