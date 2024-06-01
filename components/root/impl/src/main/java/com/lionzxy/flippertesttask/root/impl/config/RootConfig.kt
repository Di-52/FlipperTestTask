package com.lionzxy.flippertesttask.root.impl.config

import kotlinx.serialization.Serializable

/**
 * @author Demitrist on 19.03.2024
 **/

@Serializable
sealed class RootConfig {

    @Serializable
    data object MainConfig : RootConfig()

    @Serializable
    data class KeyConfig(val lockerId: Int) : RootConfig()
}