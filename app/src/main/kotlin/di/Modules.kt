package di

import NicksViewModel
import Repository
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::NicksViewModel)
}

val repositoryModule = module {
    singleOf(::Repository)
}

val appModule = module {

}
