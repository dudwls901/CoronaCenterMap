package com.kyj.presentation.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyj.domain.usecase.DownloadCoronaCentersUseCase
import com.kyj.domain.usecase.InsertCoronaCentersUseCase
import com.kyj.domain.util.NetworkResult
import com.kyj.domain.util.getErrorMessage
import com.kyj.presentation.common.util.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val downloadCoronaCentersUseCase: DownloadCoronaCentersUseCase,
    private val insertCoronaCentersUseCase: InsertCoronaCentersUseCase,
) : ViewModel() {

    private var _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage

    suspend fun getAllCoronaCenters(maxPage: Int) = withContext(viewModelScope.coroutineContext) {
        var isSuccess = true
        for (page in 1..maxPage) {
            isSuccess = getEachPageCoronaCenters(page)
            if (!isSuccess) break
        }
        isSuccess
    }

    private suspend fun getEachPageCoronaCenters(page: Int): Boolean = withContext(viewModelScope.coroutineContext) {
        var isSuccess: Boolean
        with(downloadCoronaCentersUseCase(page)) {
            isSuccess = when (this) {
                is NetworkResult.Success -> {
                    insertCoronaCentersUseCase(this.value.data.toTypedArray())
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