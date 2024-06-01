package com.lionzxy.flippertesttask.keychoose.impl.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lionzxy.flippertesttask.keychoose.impl.config.KeyModel
import kotlinx.collections.immutable.PersistentSet

/**
 * @author Demitrist on 19.03.2024
 **/

@Composable
fun KeyComposableScreen(
    keySet: PersistentSet<KeyModel>,
    onKeySelect: (Int) -> Unit,
) {
    val keyList = remember(keySet) { keySet.toList() }

    LazyColumn {
        items(
            items = keyList,
            key = { it.keyNumber }
        ) { key ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { onKeySelect(key.keyNumber) }
            ) {
                Text(
                    text = "Key #${key.keyNumber}",
                    color = Color.White,
                )
            }
        }
    }
}