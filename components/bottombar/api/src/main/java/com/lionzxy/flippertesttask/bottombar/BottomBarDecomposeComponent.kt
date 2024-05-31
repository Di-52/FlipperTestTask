package com.lionzxy.flippertesttask.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

abstract class BottomBarDecomposeComponent {
    @Composable
    abstract fun Render(modifier: Modifier)
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onLockerClick: (Int) -> Value<Int>,
        ): BottomBarDecomposeComponent
    }
}