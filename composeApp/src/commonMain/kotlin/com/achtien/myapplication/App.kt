package com.achtien.brightai

import CatFactsViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.achtien.catfacts.Fact
import com.achtien.myapplication.BluetoothScreen
import com.achtien.myapplication.ui.theme.BrightAITheme
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var isBluetoothSelected by remember { mutableStateOf(true) }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding()) {
            if (isBluetoothSelected) {
                PermissionsAndBluetooth() {
                    isBluetoothSelected = !isBluetoothSelected
                }
            } else {
                CatFactsListView(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    isBluetoothSelected = !isBluetoothSelected
                }
            }
        }
    }
}

@Composable
fun PermissionsAndBluetooth(onCatFactsSelected: () -> Unit) {
    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val controller: PermissionsController = remember(factory) { factory.createPermissionsController() }
    BindEffect(controller)

    BluetoothScreen(controller = controller, onCatFactsSelected = onCatFactsSelected)
}

@Composable
fun CatFactsListView(modifier: Modifier = Modifier, switchToBluetooth: () -> Unit) {
    val viewModel = koinViewModel<CatFactsViewModel>()
    val catFacts = viewModel.catFacts.collectAsState().value
    val scope = rememberCoroutineScope()

    CatFactsListView(
        catFacts = catFacts, modifier = modifier,
        loadMore = {
            scope.launch {
                viewModel.loadMore()
            }
        },
        switchToBluetooth = switchToBluetooth,
        clearCache = viewModel::clearCache
    )
}

@Composable
fun CatFactsListView(
    catFacts: List<Fact>,
    modifier: Modifier = Modifier,
    loadMore: () -> Unit,
    switchToBluetooth: () -> Unit,
    clearCache: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (catFacts.isEmpty()) {
                item {
                    Loading()
                }
            } else {
                item {
                    Row(Modifier.fillMaxWidth()) {
                        Button(onClick = switchToBluetooth) {
                            Text(text = "Switch to Bluetooth")
                        }
                        Button(onClick = clearCache) {
                            Text(text = "Clear Cache")
                        }
                    }
                }

                items(catFacts) {
                    CatFact(it)
                }
                item {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Button(onClick = loadMore) {
                            Text(text = "Load Next Page")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCatFacts() {
    BrightAITheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CatFactsListView(
                catFacts = listOf<Fact>(
                    Fact(id = 1, "Cat fact 1"),
                    Fact(id = 2, "Cat fact 2"),
                    Fact(id = 3, "Cat fact 3"),
                ),
                modifier = Modifier.padding(innerPadding),
                loadMore = {},
                clearCache = {},
                switchToBluetooth = {},
            )
        }
    }
}

@Composable
fun CatFact(catFact: Fact, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Text(
            text = catFact.message,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier)
    }
}
