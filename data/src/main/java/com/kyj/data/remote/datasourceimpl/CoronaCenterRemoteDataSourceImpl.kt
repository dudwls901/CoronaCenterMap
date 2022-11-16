package com.kyj.data.remote.datasourceimpl

import com.kyj.data.common.util.safeApiCall
import com.kyj.data.remote.datasource.CoronaCenterRemoteDataSource
import com.kyj.data.remote.api.CoronaCenterApiService
import com.kyj.data.remote.response.CoronaCenterResponse
import com.kyj.domain.util.NetworkResult
import javax.inject.Inject

class CoronaCenterRemoteDataSourceImpl @Inject constructor(
    private val coronaCenterApiService: CoronaCenterApiService,
) : CoronaCenterRemoteDataSource {
    override suspend fun downloadCoronaCenters(page: Int): NetworkResult<CoronaCenterResponse> {
        return safeApiCall { coronaCenterApiService.downloadCoronaCenters(page) }
    }
}