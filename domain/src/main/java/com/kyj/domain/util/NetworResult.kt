package com.kyj.domain.util

sealed class NetworkResult<out T> {
    data class Success<T>(val value: T) : NetworkResult<T>()
    data class Fail(val message: String) : NetworkResult<Nothing>()
    data class Exception(val errorType: ErrorType) : NetworkResult<Nothing>()
}

private fun <T> NetworkResult<T>.toModel(): T {
    return (this as NetworkResult.Success).value
}

private fun <R> changeNetworkResult(replaceData: R): NetworkResult<R> {
    return NetworkResult.Success(replaceData)
}

fun <T, R> NetworkResult<T>.mapDomainModel(getData: (T) -> R): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> changeNetworkResult(getData(toModel()))
        is NetworkResult.Fail -> this
        is NetworkResult.Exception -> this
    }
}