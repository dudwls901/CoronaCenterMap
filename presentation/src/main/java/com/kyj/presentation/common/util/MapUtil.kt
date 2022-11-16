package com.kyj.presentation.common.util

import android.graphics.Color
import com.kyj.domain.model.CenterType
import com.naver.maps.map.NaverMapSdk.ClientUnspecifiedException
import com.naver.maps.map.NaverMapSdk.QuotaExceededException
import com.naver.maps.map.NaverMapSdk.UnauthorizedClientException

fun Exception.getNaverMapErrorMessage() = when (this) {
    is UnauthorizedClientException -> "잘못된 클라이언트 오류"
    is QuotaExceededException -> "사용한도 초과 혹은 콘솔 Maps 서비스 미선택 오륲"
    is ClientUnspecifiedException -> "클라이언트 ID 미지정 오류"
    else -> "알 수 없는 오류"
}

fun CenterType.getColor(): Int = when (this) {
    CenterType.CENTER -> Color.GREEN
    CenterType.REGION -> Color.BLUE
}