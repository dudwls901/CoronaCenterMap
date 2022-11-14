package com.kyj.data.datasourceimpl

import com.kyj.data.datasource.CoronaCenterRemoteDataSource
import com.kyj.data.remote.api.CoronaCenterApiService
import com.kyj.data.remote.response.CoronaCenterResponse
import retrofit2.Response
import javax.inject.Inject

class CoronaCenterRemoteDataSourceImpl @Inject constructor(
    private val coronaCenterApiService: CoronaCenterApiService,
) : CoronaCenterRemoteDataSource {
    override suspend fun getCoronaCenters(page: Int): Response<CoronaCenterResponse> {
        return coronaCenterApiService.getCoronaCenters(page)
    }
}