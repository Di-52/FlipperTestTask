package com.lionzxy.flippertesttask.lockerchoose.impl.config

import kotlinx.serialization.Serializable

/**
 * @author Demitrist on 20.03.2024
 **/

@Serializable
data class LockerViewModelState(val list: Set<LockerModel>)