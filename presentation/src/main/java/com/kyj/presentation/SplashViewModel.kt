package com.kyj.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyj.domain.usecase.GetCoronaCentersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCoronaCentersUseCase: GetCoronaCentersUseCase,
) : ViewModel() {

    fun getCoronaCenters() = viewModelScope.launch {
        Timber.d("${getCoronaCentersUseCase(1)}")
    }
}