package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.BuildConfig
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiClient
import br.com.leonardomiyagi.kotlinbaseapplication.data.api.ApiService
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.API_BASE_STAGING_URL
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.API_BASE_URL
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.DEBUG
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.DI_API_BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module
class ApiModule {

    @Provides
    @Named(DI_API_BASE_URL)
    fun provideBaseUrl(): String {
        return if (BuildConfig.BUILD_TYPE.equals(DEBUG, true) ||
                BuildConfig.BUILD_TYPE.equals(DEBUG, true)) {
            API_BASE_STAGING_URL
        } else {
            API_BASE_URL
        }
    }

    @Provides
    fun provideHtppLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Provides
    fun provideRetrofit(@Named(DI_API_BASE_URL) baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideApiClient(apiService: ApiService): ApiClient {
        return ApiClient(apiService)
    }
}