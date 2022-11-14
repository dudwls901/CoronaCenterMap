package com.kyj.data.repositoryimpl

import com.kyj.data.common.util.safeApiCall
import com.kyj.data.datasource.CoronaCenterRemoteDataSource
import com.kyj.domain.repository.CoronaCenterRepository
import com.kyj.domain.util.NetworkResult
import javax.inject.Inject

class CoronaCenterRepositoryImpl @Inject constructor(
    private val coronaCenterRemoteDataSource: CoronaCenterRemoteDataSource,
) : CoronaCenterRepository {
    override suspend fun getCoronaCenters(page: Int): NetworkResult {
        return safeApiCall { coronaCenterRemoteDataSource.getCoronaCenters(page) }
    }
}