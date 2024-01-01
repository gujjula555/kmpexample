package com.jiostb.myapplication.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun appModule() = listOf(httpClientModule, repositoryModule, viewModel)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules (
            httpClientModule, repositoryModule, viewModel
        )
    }