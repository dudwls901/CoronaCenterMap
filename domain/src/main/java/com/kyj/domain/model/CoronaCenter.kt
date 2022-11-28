package com.kyj.domain.model

data class CoronaCenter(
    val id: Int,
    val address: String,
    val centerName: String,
    val centerType: CenterType,
    val facilityName: String,
    val lat: Double,
    val lng: Double,
    val phoneNumber: String,
    val updatedAt: String,
)

fun parseToCenterTypeEnum(centerType: String): CenterType = when (centerType) {
     CenterType.CENTER.type -> CenterType.CENTER
    else -> CenterType.REGION

}

enum class CenterType(val type: String) {
    CENTER("중앙/권역"), REGION("지역")
}