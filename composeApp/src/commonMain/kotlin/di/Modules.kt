package di

import CatFactsRepository
import CatFactsViewModel
import com.achtien.myapplication.BluetoothScreenViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::CatFactsViewModel)
    viewModelOf(::BluetoothScreenViewModel)
}

val repositoryModule = module {
    singleOf(::CatFactsRepository)
}
