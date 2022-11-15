package com.kyj.domain.model

data class CoronaCenter(
    val id: Int,
    val address: String,
    val centerName: String,
    val centerType: String,
    val facilityName: String,
    val lat: String,
    val lng: String,
    val phoneNumber: String,
    val updatedAt: String,
)