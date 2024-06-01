package com.lionzxy.flippertesttask.lockerchoose.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.flipperdevices.core.decompose.ScreenDecomposeComponent

abstract class LockerChooseDecomposeComponent(
    componentContext: ComponentContext,
) : ScreenDecomposeComponent(componentContext) {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            tabName: String,
            onLockerClick: (Int) -> Value<Int>,
        ): LockerChooseDecomposeComponent
    }
}