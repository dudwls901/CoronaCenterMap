package com.kyj.domain.util

enum class ErrorType {
    NETWORK,
    TIMEOUT,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN
}

fun ErrorType.getErrorMessage(): String = when(this){
    ErrorType.NETWORK -> "네트워크 에러"
    ErrorType.TIMEOUT -> "요청 시간 초과"
    ErrorType.UNKNOWN -> "알 수 없는 문제 발생"
    ErrorType.SERVER_ERROR -> "서버 에러 발생"
    ErrorType.SERIALIZATION_ERROR -> "직렬화 문제 발생"
}