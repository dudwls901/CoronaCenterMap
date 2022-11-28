package com.kyj.presentation.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyj.domain.usecase.DownloadCoronaCentersUseCase
import com.kyj.domain.util.NetworkResult
import com.kyj.domain.util.getErrorMessage
import com.kyj.presentation.common.util.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val downloadCoronaCentersUseCase: DownloadCoronaCentersUseCase,
) : ViewModel() {

    private var _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage

    suspend fun getAllCoronaCenters() = withContext(viewModelScope.coroutineContext) {
        var isSuccess: Boolean
        with(downloadCoronaCentersUseCase()) {
            isSuccess = when (this) {
                is NetworkResult.Success -> {
                    true
                }
                is NetworkResult.Fail -> {
                    _errorMessage.value = Event(this.message)
                    false
                }
                is NetworkResult.Exception -> {
                    _errorMessage.value = Event(this.errorType.getErrorMessage())
                    false
                }
            }
        }
        isSuccess
    }
}