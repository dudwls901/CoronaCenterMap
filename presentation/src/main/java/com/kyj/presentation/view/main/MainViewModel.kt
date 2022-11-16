package com.kyj.presentation.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kyj.domain.model.CoronaCenter
import com.kyj.domain.usecase.GetCoronaCentersUseCase
import com.naver.maps.map.overlay.Marker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    getCoronaCentersUseCase: GetCoronaCentersUseCase,
) : ViewModel() {
    val coronaCenters: LiveData<Map<Int, CoronaCenterUi>> = getCoronaCentersUseCase().map { coronaCenters ->
        coronaCenters.associate { coronaCenter ->
            Pair(
                coronaCenter.id,
                parseCoronaCenterToCoronaCenterUi(coronaCenter)
            )
        }
    }.asLiveData()

    private val _selectedCoronaCenter = MutableLiveData<CoronaCenterUi>()
    val selectedCoronaCenter: LiveData<CoronaCenterUi>
        get() = _selectedCoronaCenter

    fun updateSelectedCoronaCenter(id: Int) {
        _selectedCoronaCenter.value = coronaCenters.value?.get(id)
    }

    private fun parseCoronaCenterToCoronaCenterUi(coronaCenter: CoronaCenter) = CoronaCenterUi(
        Marker(),
        coronaCenter
    )
}