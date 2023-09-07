package com.afisdev.common.datasource

import android.accounts.NetworkErrorException
import com.afisdev.common.model.ResultState
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeoutException

object DataSourceHelper {
    suspend fun <T> getResult(call: suspend () -> Response<T>): ResultState<T> {
        return ResultState.run {
            try {
                val response = call()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let { success(body) }
                }
                error("${response.code()} ${response.message()}")
            } catch (e: Exception) {
                return when (e) {
                    is IOException -> error("No Internet Connection!")
                    is TimeoutException -> error("Connection Timeout!")
                    is NetworkErrorException -> error("Network Error!")
                    else -> error(e.message ?: e.toString())
                }
            }
        }
    }
}