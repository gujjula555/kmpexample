package com.jiostb.myapplication.di

import com.jiostb.myapplication.viewmodel.MainViewModel
import io.ktor.client.engine.android.Android
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun platformModule(): Module = module {
    //single { DriverFactory(get()) }

    single {
        Android.create()
    }

    viewModel {
        MainViewModel(get())
    }
}