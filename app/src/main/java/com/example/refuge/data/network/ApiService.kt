package com.example.refuge.data.network

import com.example.refuge.data.db.entities.Restroom
import com.example.refuge.data.network.responses.RestroomResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("v1/restrooms?page=1&per_page=10&offset=0")
    suspend fun getRestrooms(): Response<List<Restroom>>

    companion object {
        operator fun invoke(networkInterceptor: NetworkConnectionInterceptor): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.refugerestrooms.org/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}