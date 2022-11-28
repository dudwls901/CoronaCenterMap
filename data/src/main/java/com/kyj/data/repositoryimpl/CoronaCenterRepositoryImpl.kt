package com.kyj.data.repositoryimpl

import com.kyj.data.common.constant.END_PAGE
import com.kyj.data.common.constant.START_PAGE
import com.kyj.data.local.datasource.CoronaCenterLocalDataSource
import com.kyj.data.local.dto.toEntity
import com.kyj.data.remote.datasource.CoronaCenterRemoteDataSource
import com.kyj.domain.model.CoronaCenter
import com.kyj.domain.model.CoronaCentersInfo
import com.kyj.domain.repository.CoronaCenterRepository
import com.kyj.domain.util.ErrorType
import com.kyj.domain.util.NetworkResult
import com.kyj.domain.util.mapDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoronaCenterRepositoryImpl @Inject constructor(
    private val coronaCenterRemoteDataSource: CoronaCenterRemoteDataSource,
    private val coronaCenterLocalDataSource: CoronaCenterLocalDataSource,
) : CoronaCenterRepository {
    override suspend fun downloadCoronaCenters(): NetworkResult<CoronaCentersInfo> {
        var downloadResult: NetworkResult<CoronaCentersInfo>? = null
        for (page in START_PAGE..END_PAGE) {
            downloadResult = downloadCoronaCentersForPages(page)
            when (downloadResult) {
                is NetworkResult.Success -> {
                    insertCenters(downloadResult.value.data)
                }
                else -> {
                    break
                }
            }
        }
        return try {
            downloadResult!!
        } catch (e: Exception) {
            NetworkResult.Exception(ErrorType.UNKNOWN)
        }
    }

    override fun getCoronaCenters(): Flow<List<CoronaCenter>> {
        return coronaCenterLocalDataSource.getCoronaCenters().map { coronaCenters ->
            coronaCenters.map { it.toDomainModel() }
        }
    }

    private suspend fun insertCenters(coronaCenters: List<CoronaCenter>) {
        coronaCenterLocalDataSource.insertCenters(*coronaCenters.map { it.toEntity() }.toTypedArray())
    }

    private suspend fun downloadCoronaCentersForPages(page: Int): NetworkResult<CoronaCentersInfo> {
        return coronaCenterRemoteDataSource.downloadCoronaCenters(page).mapDomainModel { it.toDomainModel() }
    }
}