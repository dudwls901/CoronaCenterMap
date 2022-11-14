package com.kyj.data.repositoryimpl

import com.kyj.data.datasource.CoronaCenterRemoteDataSource
import com.kyj.domain.model.CoronaCentersInfo
import com.kyj.domain.repository.CoronaCenterRepository
import com.kyj.domain.util.NetworkResult
import com.kyj.domain.util.mapDomainModel
import javax.inject.Inject

class CoronaCenterRepositoryImpl @Inject constructor(
    private val coronaCenterRemoteDataSource: CoronaCenterRemoteDataSource,
) : CoronaCenterRepository {
    override suspend fun getCoronaCenters(page: Int): NetworkResult<CoronaCentersInfo> {
        return coronaCenterRemoteDataSource.getCoronaCenters(page).mapDomainModel { it.toDomainModel() }
    }
}