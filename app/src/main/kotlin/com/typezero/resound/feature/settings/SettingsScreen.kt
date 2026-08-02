package com.typezero.resound.feature.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.SystemUpdate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.typezero.resound.feature.about.AboutDialog
import com.typezero.resound.feature.about.UpdateDialog
import com.typezero.resound.ui.components.ResoundCard
import com.typezero.resound.ui.theme.Signal
import com.typezero.resound.ui.theme.TextLo

private const val CHANGELOG_URL =
    "https://github.com/MikereDD/It-Works-On-My-Machine/blob/main/Android/Resound/CHANGELOG.md"

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var showAbout by remember { mutableStateOf(false) }
    var showUpdater by remember { mutableStateOf(false) }

    val version = remember {
        runCatching {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        }.getOrNull() ?: "—"
    }

    fun open(url: String) {
        runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineSmall)
        Text(
            "Resound preferences, updates, and application information",
            style = MaterialTheme.typography.bodySmall,
            color = TextLo,
        )

        Spacer(Modifier.height(4.dp))

        ResoundCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text("Application", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(14.dp))

                SettingsRow(
                    icon = { Icon(Icons.Outlined.SystemUpdate, contentDescription = null) },
                    title = "Check for updates",
                    subtitle = "Installed version $version",
                    onClick = { showUpdater = true },
                )

                SettingsRow(
                    icon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                    title = "About Resound",
                    subtitle = "Credits, project details, and licensing",
                    onClick = { showAbout = true },
                )

                SettingsRow(
                    icon = { Icon(Icons.Outlined.OpenInNew, contentDescription = null) },
                    title = "View changelog",
                    subtitle = "See release notes and project history",
                    onClick = { open(CHANGELOG_URL) },
                )
            }
        }

        ResoundCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text("Audio workspace", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Additional editor, export, and appearance controls will be introduced here as the premium interface evolves.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextLo,
                )
            }
        }
    }

    if (showAbout) AboutDialog(onDismiss = { showAbout = false })
    if (showUpdater) UpdateDialog(onDismiss = { showUpdater = false })
}

@Composable
private fun SettingsRow(
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        CompositionLocalProvider(androidx.compose.material3.LocalContentColor provides Signal) {
            icon()
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = TextLo)
        }
    }
}
