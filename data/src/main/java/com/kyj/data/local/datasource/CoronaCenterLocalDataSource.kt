package com.kyj.data.local.datasource

import com.kyj.data.local.dto.CoronaCenterEntity
import kotlinx.coroutines.flow.Flow

interface CoronaCenterLocalDataSource {
    fun getCoronaCenters(): Flow<List<CoronaCenterEntity>>
    suspend fun insertCenters(vararg coronaCenterEntity: CoronaCenterEntity)
}