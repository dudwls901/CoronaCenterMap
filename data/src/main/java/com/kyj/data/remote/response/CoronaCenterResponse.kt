package com.kyj.data.remote.response

import com.kyj.domain.model.CoronaCentersInfo
import kotlinx.serialization.Serializable

@Serializable
data class CoronaCenterResponse(
    val currentCount: Int,
    val data: List<CoronaCenter>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int,
) {
    fun toDomainModel(): CoronaCentersInfo = CoronaCentersInfo(
        currentCount = currentCount,
        data = data.map { it.toDomainModel() },
        matchCount = matchCount,
        page = page,
        perPage = perPage,
        totalCount = totalCount
    )
}