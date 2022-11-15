package com.kyj.domain.model

data class CoronaCentersInfo(
    val currentCount: Int,
    val data: List<CoronaCenter>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int,
)