package com.achtien.codingtemplate.di

import Repository
import com.achtien.codingtemplate.NicksViewModel
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
