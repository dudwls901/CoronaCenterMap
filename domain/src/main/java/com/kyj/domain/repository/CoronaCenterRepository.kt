package com.kyj.domain.repository

import com.kyj.domain.util.NetworkResult

interface CoronaCenterRepository {
    suspend fun getCoronaCenters(page: Int): NetworkResult
}