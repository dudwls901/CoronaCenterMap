package com.kyj.domain.repository

import com.kyj.domain.model.CoronaCenter
import com.kyj.domain.model.CoronaCentersInfo
import com.kyj.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CoronaCenterRepository {
    suspend fun downloadCoronaCenters(): NetworkResult<CoronaCentersInfo>
    fun getCoronaCenters(): Flow<List<CoronaCenter>>
}