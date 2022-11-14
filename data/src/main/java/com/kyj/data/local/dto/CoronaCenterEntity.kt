package com.kyj.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kyj.data.common.constant.CORONA_CENTER_TABLE

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
)