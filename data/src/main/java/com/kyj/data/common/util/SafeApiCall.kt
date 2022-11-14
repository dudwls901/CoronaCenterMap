package com.kyj.data.common.util

import com.kyj.data.common.constant.ERROR_KEY
import com.kyj.data.common.constant.MESSAGE_KEY
import com.kyj.domain.util.ErrorType
import com.kyj.domain.util.NetworkResult
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
        NetworkResult.Success(response.body()!!)
    } catch (e: Exception) {
        e.printStackTrace()
        Timber.e("Call error: ${e.localizedMessage}", e.cause)
        when (e) {
            is HttpException -> {
                val body = e.response()?.errorBody()
                NetworkResult.Fail(getErrorMessage(body))
            }
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
            jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
            else -> "Something wrong happened"
        }
    } catch (e: Exception) {
        "Something wrong happened"
    }
}