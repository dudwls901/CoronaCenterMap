package com.kyj.presentation.view.main

import com.kyj.domain.model.CoronaCenter
import com.naver.maps.map.overlay.Marker

data class CoronaCenterUi(
    val marker: Marker,
    val coronaCenter: CoronaCenter,
)