package com.kyj.data.local.datasourceimpl

import com.kyj.data.local.dao.CoronaCenterDao
import com.kyj.data.local.datasource.CoronaCenterLocalDataSource
import com.kyj.data.local.dto.CoronaCenterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoronaCenterLocalDataSourceImpl @Inject constructor(
    private val coronaCenterDao: CoronaCenterDao,
) : CoronaCenterLocalDataSource {
    override fun getCoronaCenters(): Flow<List<CoronaCenterEntity>> =
        coronaCenterDao.getCoronaCenters()

    override suspend fun insertCenters(vararg coronaCenterEntity: CoronaCenterEntity) {
        coronaCenterDao.insertCenters(*coronaCenterEntity)
    }
}