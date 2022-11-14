package com.kyj.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kyj.data.local.dao.CoronaCenterDao
import com.kyj.data.local.dto.CoronaCenterEntity

@Database(
    entities = [CoronaCenterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoronaCenterDatabase : RoomDatabase() {
    abstract fun coronaCenterDao(): CoronaCenterDao
}