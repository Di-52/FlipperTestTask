package com.lionzxy.flippertesttask.root.impl.config

import com.lionzxy.flippertesttask.bottombar.BottomBarDecomposeComponent
import com.lionzxy.flippertesttask.choosekey.api.ChooseKeyDecomposeComponent

/**
 * @author Demitrist on 19.03.2024
 **/

sealed interface Child {

    class MainChild(val component: BottomBarDecomposeComponent) : Child

    class KeyChild(val component: ChooseKeyDecomposeComponent) : Child
}