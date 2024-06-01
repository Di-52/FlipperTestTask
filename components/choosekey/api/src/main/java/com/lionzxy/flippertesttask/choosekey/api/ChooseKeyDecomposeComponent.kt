package com.lionzxy.flippertesttask.choosekey.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.core.decompose.ScreenDecomposeComponent

/**
 * @author Demitrist on 19.03.2024
 **/

abstract class ChooseKeyDecomposeComponent(
    componentContext: ComponentContext,
) : ScreenDecomposeComponent(componentContext) {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            lockerId: Int,
            onKeyChoose: (Int) -> Unit,
        ): ChooseKeyDecomposeComponent
    }
}