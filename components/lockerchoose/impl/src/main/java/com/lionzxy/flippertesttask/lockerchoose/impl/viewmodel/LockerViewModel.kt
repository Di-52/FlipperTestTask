package com.lionzxy.flippertesttask.lockerchoose.impl.viewmodel

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.statekeeper.SerializableContainer
import com.lionzxy.flippertesttask.lockerchoose.impl.config.LockerModel
import com.lionzxy.flippertesttask.lockerchoose.impl.config.LockerViewModelState
import kotlinx.collections.immutable.toPersistentSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LockerViewModel(val savedState: SerializableContainer?) : InstanceKeeper.Instance {

    private val lockers = mutableSetOf<LockerModel>()

    init {
        lockers.addAll(
            savedState?.let {
                it.consume(strategy = LockerViewModelState.serializer())?.list
            } ?: (0..15).map { LockerModel(it, null) })
    }

    fun getLockers() = MutableStateFlow(lockers.sortedBy { it.lockerNumber }
        .toPersistentSet())
        .asStateFlow()

    fun setKey(locker: Int, key: Int) {
        if (key >= 0) {
            lockers.removeIf { it.lockerNumber == locker }
            lockers.add(LockerModel(locker, key))
        }
    }

    fun saveState(): SerializableContainer =
        SerializableContainer(value = LockerViewModelState(lockers), strategy = LockerViewModelState.serializer())
}