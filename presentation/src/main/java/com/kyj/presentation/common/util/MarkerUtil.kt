package com.kyj.presentation.common.util

import android.graphics.Color
import com.kyj.domain.model.CenterType


fun CenterType.getColor(): Int = when (this) {
    CenterType.CENTER -> Color.GREEN
    CenterType.REGION -> Color.BLUE
}