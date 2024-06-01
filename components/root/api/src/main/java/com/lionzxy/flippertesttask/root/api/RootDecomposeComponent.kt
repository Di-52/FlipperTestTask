package com.lionzxy.flippertesttask.root.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext

/**
 * @author Demitrist on 19.03.2024
 **/

abstract class RootDecomposeComponent {
    @Composable
    abstract fun Render(modifier: Modifier)
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ): RootDecomposeComponent
    }
}