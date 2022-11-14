package com.kyj.data.remote.response

data class CoronaCenterResponse(
    val currentCount: Int,
    val `data`: List<CoronaCenter>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)