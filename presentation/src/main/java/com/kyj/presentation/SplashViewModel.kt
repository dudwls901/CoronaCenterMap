package com.kyj.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyj.domain.usecase.GetCoronaCentersUseCase
import com.kyj.domain.usecase.InsertCoronaCentersUseCase
import com.kyj.domain.util.NetworkResult
import com.kyj.domain.util.getErrorMessage
import com.kyj.presentation.util.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCoronaCentersUseCase: GetCoronaCentersUseCase,
    private val insertCoronaCentersUseCase: InsertCoronaCentersUseCase,
) : ViewModel() {

    private var _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage

    suspend fun getAllCoronaCenters(maxPage: Int) = viewModelScope.async {
        var isSuccess = true
        for (page in 1..maxPage) {
            isSuccess = getEachPageCoronaCenters(page)
            if (!isSuccess) break
        }
        isSuccess
    }.await()

    private suspend fun getEachPageCoronaCenters(page: Int): Boolean = viewModelScope.async {
        var isSuccess: Boolean
        with(getCoronaCentersUseCase(page)) {
            Timber.d("$this")
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
    }.await()
}