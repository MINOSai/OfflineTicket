package com.minosai.remote.util

import com.minosai.model.Result
import retrofit2.Response
import java.lang.Exception

open class BaseApiClient {

    protected suspend fun <T> getResult(request: suspend () -> Response<T>): Result<T> {
        try {
            val response = request()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.error("Server response error")
                }
            } else {
                Result.error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            val errorMessage = e.message ?: e.toString()
            return Result.error("Network request failed. Reason: $errorMessage")
        }
    }

}