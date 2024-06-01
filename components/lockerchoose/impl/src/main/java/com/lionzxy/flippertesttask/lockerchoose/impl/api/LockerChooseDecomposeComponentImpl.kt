package com.lionzxy.flippertesttask.lockerchoose.impl.api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.statekeeper.SerializableContainer
import com.lionzxy.flippertesttask.core.di.AppGraph
import com.lionzxy.flippertesttask.lockerchoose.api.LockerChooseDecomposeComponent
import com.lionzxy.flippertesttask.lockerchoose.impl.composable.LockerComposableScreen
import com.lionzxy.flippertesttask.lockerchoose.impl.viewmodel.LockerViewModel
import com.squareup.anvil.annotations.ContributesBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

private const val STATE_KEEPER_KEY = "SAVED_STATE"

class LockerChooseDecomposeComponentImpl @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val tabName: String,
    @Assisted private val onLockerClick: (Int) -> Value<Int>,
) : LockerChooseDecomposeComponent(componentContext) {

    private val lockerViewModel = instanceKeeper.getOrCreate {
        LockerViewModel(
            savedState = stateKeeper.consume(
                key = STATE_KEEPER_KEY,
                strategy = SerializableContainer.serializer()
            )
        )
    }

    init {
        stateKeeper.register(
            key = STATE_KEEPER_KEY,
            strategy = SerializableContainer.serializer(),
            supplier = lockerViewModel::saveState,
        )
    }

    @Composable
    override fun Render() {
        val lockerSet by lockerViewModel.getLockers().collectAsState()
        Column(
            Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "$tabName tab",
                fontSize = 32.sp,
                textAlign = TextAlign.Start
            )
            LockerComposableScreen(lockerSet) { lockerId ->
                onLockerClick(lockerId).subscribe {
                    lockerViewModel.setKey(lockerId, it)
                }
            }
        }
    }

    @AssistedFactory
    @ContributesBinding(AppGraph::class, LockerChooseDecomposeComponent.Factory::class)
    interface Factory : LockerChooseDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            tabName: String,
            onLockerClick: (Int) -> Value<Int>,
        ): LockerChooseDecomposeComponentImpl
    }
}