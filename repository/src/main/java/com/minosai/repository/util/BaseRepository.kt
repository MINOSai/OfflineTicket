package com.minosai.repository.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.minosai.model.Result
import kotlinx.coroutines.Dispatchers

open class BaseRepo {

    protected fun <T> makeRequest(
        onSuccess: suspend (data: T?) -> Unit = {},
        onError: suspend (message: String?) -> Unit = {},
        request: suspend () -> Result<T>
    ): LiveData<Result<T?>> =
        liveData(Dispatchers.IO) {
            emit(Result.loading())

            val response = request.invoke()

            when (response.status) {
                Result.Status.SUCCESS -> {
                    emit(Result.success(response.data))
                    onSuccess.invoke(response.data)
                }
                Result.Status.ERROR -> {
                    emit(Result.error(response.message!!))
                    onError.invoke(response.message)
                }
                else -> {

                }
            }
        }

    protected fun <T, A> makeRequestAndSave(
        databaseQuery: () -> LiveData<T>,
        networkCall: suspend () -> Result<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<Result<T>> = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val source = databaseQuery.invoke().map { Result.success(it) }
        emitSource(source)

        val response = networkCall.invoke()
        when (response.status) {
            Result.Status.SUCCESS -> {
                saveCallResult(response.data!!)
            }
            Result.Status.ERROR -> {
                emit(Result.error(response.message!!))
                emitSource(source)
            }
            else -> {

            }
        }
    }
}