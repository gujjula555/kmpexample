package com.jiostb.myapplication.di

import com.jiostb.myapplication.domain.repository.Repository
import com.jiostb.myapplication.utils.Constant
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val httpClientModule = module {
    single {
         HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    explicitNulls = false
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                    allowSpecialFloatingPointValues = true
                })
            }


            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(HttpTimeout) {
                connectTimeoutMillis = Constant.TIMEOUT
                requestTimeoutMillis = Constant.TIMEOUT
                socketTimeoutMillis = Constant.TIMEOUT
            }
        }
    }
}


val repositoryModule = module {
    single<Repository> { Repository(get()) }
}

//expect fun isNetworkAvailable():Boolean