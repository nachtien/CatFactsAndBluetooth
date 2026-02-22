package com.achtien.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achtien.myapplication.scan.BrightAIScanner.Companion.scanner
import com.juul.kable.PlatformAdvertisement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BluetoothScreenViewModel : ViewModel() {
    private val seen = mutableMapOf<String, PlatformAdvertisement>()
    private val _peripherals = MutableStateFlow<List<PlatformAdvertisement>>(emptyList())
    val peripherals = _peripherals.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                scanner.advertisements.collect { advertisement ->
                    seen[advertisement.identifier.toString()] = advertisement
                    _peripherals.value = seen.values.toList()
                }
            } catch (e: Exception) {
                // Scanning failed (permissions not granted, Bluetooth off, etc.)
            }
        }
    }

    fun clear() {
        seen.clear()
        _peripherals.value = emptyList()
    }
}
