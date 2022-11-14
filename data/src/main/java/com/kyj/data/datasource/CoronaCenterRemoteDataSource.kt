package com.kyj.data.datasource

import com.kyj.data.remote.response.CoronaCenterResponse
import retrofit2.Response

interface CoronaCenterRemoteDataSource {
    suspend fun getCoronaCenters(
        page: Int,
    ): Response<CoronaCenterResponse>
}