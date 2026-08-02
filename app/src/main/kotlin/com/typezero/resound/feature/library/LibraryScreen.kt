package com.typezero.resound.feature.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.typezero.resound.ui.components.ResoundCard
import com.typezero.resound.ui.theme.Signal
import com.typezero.resound.ui.theme.TextLo

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Text("Library", style = MaterialTheme.typography.headlineSmall)
        Text(
            "Your Resound exports and recent projects",
            style = MaterialTheme.typography.bodySmall,
            color = TextLo,
        )

        Spacer(Modifier.height(20.dp))

        ResoundCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.LibraryMusic,
                    contentDescription = null,
                    tint = Signal,
                )
                Spacer(Modifier.height(14.dp))
                Text("Library foundation ready", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(6.dp))
                Text(
                    "Recent exports, project history, and quick reopen will live here in a future pass.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextLo,
                )
            }
        }
    }
}
