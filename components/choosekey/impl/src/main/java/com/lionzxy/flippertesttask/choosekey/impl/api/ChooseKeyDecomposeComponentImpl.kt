package com.lionzxy.flippertesttask.keychoose.impl.api

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lionzxy.flippertesttask.choosekey.api.ChooseKeyDecomposeComponent
import com.lionzxy.flippertesttask.core.di.AppGraph
import com.lionzxy.flippertesttask.keychoose.impl.composable.KeyComposableScreen
import com.lionzxy.flippertesttask.keychoose.impl.viemodel.KeyViewModel
import com.squareup.anvil.annotations.ContributesBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Provider

/**
 * @author Demitrist on 19.03.2024
 **/

class ChooseKeyDecomposeComponentImpl @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val lockerId: Int,
    @Assisted private val onKeyChoose: (Int) -> Unit,
    private val keyViewModelProvider: Provider<KeyViewModel>,
) : ChooseKeyDecomposeComponent(componentContext) {
    private val keyViewModel = instanceKeeper.getOrCreate { keyViewModelProvider.get() }

    @Composable
    override fun Render() {
        val keySet by keyViewModel.getKeys().collectAsState()
        val screenColor = Color.Blue

        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(color = screenColor)
        systemUiController.setNavigationBarColor(color = screenColor)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(screenColor)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Selecting a key for the locker #${lockerId}",
                fontSize = 32.sp,
                textAlign = TextAlign.Start,
                color = Color.White,
            )
            KeyComposableScreen(keySet = keySet) {
                onKeyChoose(it)
            }
        }
    }

    @AssistedFactory
    @ContributesBinding(AppGraph::class, ChooseKeyDecomposeComponent.Factory::class)
    interface Factory : ChooseKeyDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            lockerId: Int,
            onKeyChoose: (Int) -> Unit,
        ): ChooseKeyDecomposeComponentImpl
    }
}