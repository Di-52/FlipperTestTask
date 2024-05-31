package com.lionzxy.flippertesttask.root.impl.api

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.lionzxy.flippertesttask.bottombar.BottomBarDecomposeComponent
import com.lionzxy.flippertesttask.choosekey.api.ChooseKeyDecomposeComponent
import com.lionzxy.flippertesttask.core.di.AppGraph
import com.lionzxy.flippertesttask.root.api.RootDecomposeComponent
import com.lionzxy.flippertesttask.root.impl.composable.MainScreen
import com.lionzxy.flippertesttask.root.impl.config.Child
import com.lionzxy.flippertesttask.root.impl.config.RootConfig
import com.squareup.anvil.annotations.ContributesBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

/**
 * @author Demitrist on 19.03.2024
 **/

class RootDecomposeComponentImpl @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    private val bottomBarDecomposeComponentFactory: BottomBarDecomposeComponent.Factory,
    private val keyChooseDecomposeComponentFactory: ChooseKeyDecomposeComponent.Factory,
) : RootDecomposeComponent(), ComponentContext by componentContext {

    private val navigation = StackNavigation<RootConfig>()

    private lateinit var observableKey: MutableValue<Int>

    private val stack: Value<ChildStack<RootConfig, Child>> =
        childStack(
            source = navigation,
            serializer = RootConfig.serializer(),
            initialConfiguration = RootConfig.MainConfig,
            childFactory = ::child,
            handleBackButton = true,
        )

    @Composable
    @Suppress("NonSkippableComposable")
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()
        MainScreen(
            childStack = childStack,
            modifier = Modifier.fillMaxSize(),
        )
    }

    private fun child(
        config: RootConfig,
        componentContext: ComponentContext,
    ): Child = when (config) {

        RootConfig.MainConfig -> Child.MainChild(
            bottomBarDecomposeComponentFactory.invoke(
                componentContext = componentContext,
                onLockerClick = { id ->
                    observableKey = MutableValue(-1)
                    navigation.push(RootConfig.KeyConfig(lockerId = id))
                    observableKey
                }
            )
        )

        is RootConfig.KeyConfig -> Child.KeyChild(
            keyChooseDecomposeComponentFactory(
                componentContext = componentContext,
                lockerId = config.lockerId,
                onKeyChoose = {
                    observableKey.value = it
                    navigation.pop()
                }
            )
        )
    }

    @AssistedFactory
    @ContributesBinding(AppGraph::class, RootDecomposeComponent.Factory::class)
    fun interface Factory : RootDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
        ): RootDecomposeComponentImpl
    }
}