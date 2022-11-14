package com.kyj.coronacentermap.di

import com.kyj.data.datasource.CoronaCenterRemoteDataSource
import com.kyj.data.datasourceimpl.CoronaCenterRemoteDataSourceImpl
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
}
