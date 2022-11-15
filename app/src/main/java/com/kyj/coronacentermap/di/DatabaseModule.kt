package com.kyj.coronacentermap.di

import android.content.Context
import androidx.room.Room
import com.kyj.data.common.constant.DB_NAME
import com.kyj.data.local.CoronaCenterDatabase
import com.kyj.data.local.dao.CoronaCenterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CoronaCenterDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            CoronaCenterDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoronaCenterDao(
        coronaCenterDatabase: CoronaCenterDatabase
    ): CoronaCenterDao{
        return coronaCenterDatabase.coronaCenterDao()
    }
}