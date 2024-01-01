package com.jiostb.myapplication.di

import com.jiostb.myapplication.viewmodel.MainViewModel
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    //single { DriverFactory() }

    single {
        Darwin.create()
    }

    factory { MainViewModel(get()) }
}

object ViewModelProvider : KoinComponent {
    fun getMovieViewModel() = get<MainViewModel>()
}
