package com.kyj.coronacentermap.di

import com.kyj.data.datasource.CoronaCenterRemoteDataSource
import com.kyj.data.datasourceimpl.CoronaCenterRemoteDataSourceImpl
import com.kyj.data.local.datasource.CoronaCenterLocalDataSource
import com.kyj.data.local.datasourceimpl.CoronaCenterLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindCoronaCenterRemoteDataSource(
        coronaCenterRemoteDataSourceImpl: CoronaCenterRemoteDataSourceImpl,
    ): CoronaCenterRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCoronaCenterLocalDataSource(
        coronaCenterLocalDataSourceImpl: CoronaCenterLocalDataSourceImpl,
    ): CoronaCenterLocalDataSource

}
