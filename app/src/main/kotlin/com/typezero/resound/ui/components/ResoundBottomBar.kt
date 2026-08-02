package com.typezero.resound.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AudioFile
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.typezero.resound.ui.theme.Ink
import com.typezero.resound.ui.theme.Panel
import com.typezero.resound.ui.theme.Signal
import com.typezero.resound.ui.theme.TextLo
import androidx.compose.ui.unit.dp

enum class ResoundDestination(val label: String) {
    Editor("Editor"),
    Multitrack("Multitrack"),
    Library("Library"),
    Settings("Settings"),
}

@Composable
fun ResoundBottomBar(
    selected: ResoundDestination,
    onSelect: (ResoundDestination) -> Unit,
) {
    NavigationBar(
        containerColor = Panel,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 0.dp,
    ) {
        ResoundDestination.entries.forEach { destination ->
            val icon = when (destination) {
                ResoundDestination.Editor -> Icons.Outlined.AudioFile
                ResoundDestination.Multitrack -> Icons.Outlined.Tune
                ResoundDestination.Library -> Icons.Outlined.LibraryMusic
                ResoundDestination.Settings -> Icons.Outlined.Settings
            }
            NavigationBarItem(
                selected = selected == destination,
                onClick = { onSelect(destination) },
                icon = { Icon(icon, contentDescription = destination.label) },
                label = { Text(destination.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Ink,
                    selectedTextColor = Signal,
                    indicatorColor = Signal,
                    unselectedIconColor = TextLo,
                    unselectedTextColor = TextLo,
                ),
            )
        }
    }
}
