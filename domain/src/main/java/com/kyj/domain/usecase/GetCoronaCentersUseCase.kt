package com.kyj.domain.usecase

import com.kyj.domain.model.CoronaCentersInfo
import com.kyj.domain.repository.CoronaCenterRepository
import com.kyj.domain.util.NetworkResult
import javax.inject.Inject

class GetCoronaCentersUseCase @Inject constructor(
    private val coronaCenterRepository: CoronaCenterRepository,
) {
    suspend operator fun invoke(page: Int): NetworkResult<CoronaCentersInfo> = coronaCenterRepository.getCoronaCenters(page)
}