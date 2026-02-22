import di.repositoryModule
import di.viewModelModule
import di.bluetoothModule
import di.networkingModule
import org.koin.core.context.startKoin

fun initKoinIos() {
    startKoin {
        modules(
            viewModelModule,
            bluetoothModule(),
            repositoryModule,
            platformModule(),
            networkingModule(true)
        )
    }
}
