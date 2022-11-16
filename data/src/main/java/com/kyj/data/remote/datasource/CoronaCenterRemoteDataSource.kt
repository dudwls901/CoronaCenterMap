package com.kyj.data.remote.datasource

import com.kyj.data.remote.response.CoronaCenterResponse
import com.kyj.domain.util.NetworkResult

interface CoronaCenterRemoteDataSource {
    suspend fun downloadCoronaCenters(
        page: Int,
    ): NetworkResult<CoronaCenterResponse>
}