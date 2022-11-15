package com.kyj.data.datasource

import com.kyj.data.remote.response.CoronaCenterResponse
import com.kyj.domain.util.NetworkResult

interface CoronaCenterRemoteDataSource {
    suspend fun getCoronaCenters(
        page: Int,
    ): NetworkResult<CoronaCenterResponse>
}