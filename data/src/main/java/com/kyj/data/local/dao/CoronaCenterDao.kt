package com.kyj.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kyj.data.common.constant.CORONA_CENTER_TABLE
import com.kyj.data.local.dto.CoronaCenterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoronaCenterDao {

    @Query("SELECT * FROM $CORONA_CENTER_TABLE")
    fun getCoronaCenters(): Flow<List<CoronaCenterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCenters(vararg coronaCenterEntity: CoronaCenterEntity)
}