package di

import dev.icerock.moko.permissions.PermissionsController
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun bluetoothModule(): Module {
    return module {
        single { PermissionsController(get()) }
    }
}
