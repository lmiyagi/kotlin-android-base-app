package br.com.leonardomiyagi.kotlinbaseapplication.data.api

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by lmiyagi on 11/8/17.
 */
interface ApiService {

    @GET("/examples")
    suspend fun getExamples(): Response<String>
}