package com.lionzxy.flippertesttask.root.impl.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.lionzxy.flippertesttask.root.impl.config.Child
import com.lionzxy.flippertesttask.root.impl.config.RootConfig

/**
 * @author Demitrist on 19.03.2024
 **/

@Composable
fun MainScreen(
    childStack: ChildStack<RootConfig, Child>,
    modifier: Modifier,
) {
    Children(
        stack = childStack,
        modifier = modifier,
    ) {
        when (it.instance) {
            is Child.MainChild -> (it.instance as Child.MainChild).component.Render(modifier = modifier)
            is Child.KeyChild -> (it.instance as Child.KeyChild).component.Render()
        }
    }
}