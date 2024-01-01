package com.jiostb.myapplication.di

import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.screenModule
import com.jiostb.myapplication.HomeScreen
import com.jiostb.myapplication.HomeScreen1

sealed class BiggBossScreen: ScreenProvider {
    data object Home: BiggBossScreen()
    data class ShowDetail(val id:String) : BiggBossScreen()
}

val registerScreenModule = screenModule {
    register<BiggBossScreen.Home> {
        HomeScreen1()
    }
    /*register<BiggBossScreen.ShowDetail> { provider->
       // ShowDetailScreen(provider.id)
    }*/
}