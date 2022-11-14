package com.kyj.data.remote.api

import com.kyj.data.common.constant.CENTER
import com.kyj.data.common.constant.PER_PAGE
import com.kyj.data.remote.response.CoronaCenterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoronaCenterApiService {

    @GET(CENTER)
    suspend fun getCoronaCenters(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int = PER_PAGE,
    ): Response<CoronaCenterResponse>
}