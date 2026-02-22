package di

import dev.icerock.moko.permissions.ios.PermissionsController
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun bluetoothModule() = module {
    single { PermissionsController() }
}
