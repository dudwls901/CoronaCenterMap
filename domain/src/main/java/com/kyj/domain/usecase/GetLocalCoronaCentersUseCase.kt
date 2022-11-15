package com.kyj.domain.usecase

import com.kyj.domain.model.CoronaCenter
import com.kyj.domain.repository.CoronaCenterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalCoronaCentersUseCase @Inject constructor(
    private val coronaCenterRepository: CoronaCenterRepository,
) {
    operator fun invoke(): Flow<List<CoronaCenter>> = coronaCenterRepository.getCoronaCentersLocal()
}