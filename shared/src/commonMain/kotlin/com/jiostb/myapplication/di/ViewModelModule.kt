package com.jiostb.myapplication.di

import com.jiostb.myapplication.viewmodel.MainViewModel
import com.jiostb.myapplication.viewmodel.TabViewModel
import org.koin.dsl.module

val viewModel = module {
   /* single {
        LinkLauncher()
    }
    single {
        AnalyticLogger()
    }*/
    single {
        MainViewModel(get())
    }
    single {
        TabViewModel()
    }
    /*factory {
        ShowDetailModel(get(), get(),get())
    }

    factory {
        ParticipantDetailViewModel(get(), get(), get())
    }

    factory {
        ChartViewModel(get(), get(), get())
    }*/

}