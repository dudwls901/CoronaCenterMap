package com.kyj.data.remote.response

import com.kyj.domain.model.CoronaCenter
import kotlinx.serialization.Serializable

@Serializable
data class CoronaCenter(
    val address: String,
    val centerName: String,
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
        facilityName = facilityName,
        id = id,
        lat = lat,
        lng = lng,
        phoneNumber = phoneNumber,
        updatedAt = updatedAt
    )
}