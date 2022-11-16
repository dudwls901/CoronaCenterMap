package com.kyj.data.remote.response

import com.kyj.domain.model.CoronaCenter
import com.kyj.domain.model.parseToCenterTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class CoronaCenter(
    val address: String,
    val centerName: String,
    val centerType: String,
    val facilityName: String,
    val id: Int,
    val lat: String,
    val lng: String,
    val phoneNumber: String,
    val updatedAt: String,
) {
    fun toDomainModel(): CoronaCenter = CoronaCenter(
        address = address,
        centerName = centerName,
        centerType = parseToCenterTypeEnum(centerType),
        facilityName = facilityName,
        id = id,
        lat = lat.toDouble(),
        lng = lng.toDouble(),
        phoneNumber = phoneNumber,
        updatedAt = updatedAt
    )
}