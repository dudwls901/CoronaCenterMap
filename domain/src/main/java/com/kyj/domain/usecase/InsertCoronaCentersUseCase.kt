package com.kyj.domain.usecase

import com.kyj.domain.model.CoronaCenter
import com.kyj.domain.repository.CoronaCenterRepository
import javax.inject.Inject

class InsertCoronaCentersUseCase @Inject constructor(
    private val coronaCenterRepository: CoronaCenterRepository,
) {
    suspend operator fun invoke(coronaCenters: Array<CoronaCenter>) {
        coronaCenterRepository.insertCenters(*coronaCenters)
    }
}