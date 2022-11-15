package com.kyj.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kyj.data.common.constant.CORONA_CENTER_TABLE
import com.kyj.domain.model.CoronaCenter

@Entity(tableName = CORONA_CENTER_TABLE)
data class CoronaCenterEntity(
    @PrimaryKey val id: Int,
    val address: String,
    val centerName: String,
    val facilityName: String,
    val lat: String,
    val lng: String,
    val phoneNumber: String,
    val updatedAt: String,
) {
    fun toDomainModel(): CoronaCenter = CoronaCenter(
        id = id,
        address = address,
        centerName = centerName,
        facilityName = facilityName,
        lat = lat,
        lng = lng,
        phoneNumber = phoneNumber,
        updatedAt = updatedAt
    )
}

fun CoronaCenter.toEntity(): CoronaCenterEntity = CoronaCenterEntity(
    id = id,
    address = address,
    centerName = centerName,
    facilityName = facilityName,
    lat = lat,
    lng = lng,
    phoneNumber = phoneNumber,
    updatedAt = updatedAt
)