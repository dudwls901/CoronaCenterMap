package com.kyj.domain.util

sealed class NetworkResult {
    data class Success<T>(val data: T) : NetworkResult()
    data class Fail(val message: String) : NetworkResult()
    data class Exception(val errorType: ErrorType) :
        NetworkResult()
}