package com.kyj.coronacentermap.di

import com.kyj.data.repositoryimpl.CoronaCenterRepositoryImpl
import com.kyj.domain.repository.CoronaCenterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoronaCenterRepository(
        coronaCenterRepositoryImpl: CoronaCenterRepositoryImpl,
    ): CoronaCenterRepository
}