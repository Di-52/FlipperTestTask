package com.lionzxy.flippertesttask.lockerchoose.impl.config

import kotlinx.serialization.Serializable

@Serializable
data class LockerModel(val lockerNumber: Int = -1, val keyNumber: Int? = null)