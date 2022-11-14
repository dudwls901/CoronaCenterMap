package com.kyj.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CoronaCenterResponse(
    val currentCount: Int,
    val `data`: List<CoronaCenter>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)