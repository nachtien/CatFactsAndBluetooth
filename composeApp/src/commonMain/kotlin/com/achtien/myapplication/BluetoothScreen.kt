@file:OptIn(ExperimentalMaterial3Api::class)

package com.achtien.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juul.kable.PlatformAdvertisement
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.bluetooth.BLUETOOTH_SCAN
import dev.icerock.moko.permissions.location.LOCATION
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BluetoothScreen(controller: PermissionsController, modifier: Modifier = Modifier, onCatFactsSelected: () -> Unit) {
    val viewModel = koinViewModel<BluetoothScreenViewModel>()
    val peripherals = viewModel.peripherals.collectAsState().value
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Nearby Devices (${peripherals.size})") },
            actions = {
                IconButton(onClick = { viewModel.clear() }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Clear")
                }

                Button(onClick = onCatFactsSelected) {
                    Text("Cat Facts")
                }
            }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onClick = {
                scope.launch {
                    requestPermissions(controller)
                }
            }
        ) {
            Text("Grant Bluetooth & Location Permissions")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(peripherals, key = { it.identifier.toString() }) { advertisement ->
                PeripheralCard(advertisement)
            }
        }
    }
}

private suspend fun requestPermissions(controller: PermissionsController) {
    controller.providePermission(Permission.BLUETOOTH_SCAN)
    controller.providePermission(Permission.LOCATION)
}

@Composable
private fun PeripheralCard(advertisement: PlatformAdvertisement) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = advertisement.name ?: "Unknown",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = advertisement.identifier.toString(),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "${advertisement.rssi} dBm",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
