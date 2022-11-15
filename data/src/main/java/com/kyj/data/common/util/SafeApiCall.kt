package com.kyj.data.common.util

import com.kyj.data.common.constant.MESSAGE_KEY
import com.kyj.domain.util.ErrorType
import com.kyj.domain.util.NetworkResult
import kotlinx.serialization.SerializationException
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T> safeApiCall(callFunction: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = callFunction.invoke()
        if (response.isSuccessful) {
            NetworkResult.Success(response.body()!!)
        } else {
            if (response.code() in 400 until 500) {
                NetworkResult.Fail(getErrorMessage(response.errorBody()))
            } else {
                NetworkResult.Exception(ErrorType.SERVER_ERROR)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Timber.e("Call error: ${e.localizedMessage}", e.cause)
        when (e) {
            is HttpException -> {
                val body = e.response()?.errorBody()
                NetworkResult.Fail(getErrorMessage(body))
            }
            is SerializationException -> NetworkResult.Exception(ErrorType.SERIALIZATION_ERROR)
            is SocketTimeoutException -> NetworkResult.Exception(ErrorType.TIMEOUT)
            is IOException -> NetworkResult.Exception(ErrorType.NETWORK)
            else -> NetworkResult.Exception(ErrorType.UNKNOWN)
        }
    }
}

private fun getErrorMessage(responseBody: ResponseBody?): String {
    return try {
        val jsonObject = JSONObject(responseBody!!.string())
        when {
            jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
            else -> "Something wrong happened"
        }
    } catch (e: Exception) {
        "Something wrong happened"
    }
}