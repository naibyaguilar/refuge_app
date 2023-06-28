package com.example.refuge.data.network

import com.example.refuge.util.ApiException
import retrofit2.Response


abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {

        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val message = StringBuilder()
            message.append("Error code: ${response.errorBody()}")
            println("ErrorNAi $message")
            throw ApiException(message.toString())
        }
    }
}