package com.kyj.presentation.common.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kyj.domain.model.CoronaCenter
import com.kyj.domain.usecase.GetCoronaCentersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getCoronaCentersUseCase: GetCoronaCentersUseCase,
) : ViewModel() {
    val coronaCenters: LiveData<List<CoronaCenter>> = getCoronaCentersUseCase().asLiveData()
}