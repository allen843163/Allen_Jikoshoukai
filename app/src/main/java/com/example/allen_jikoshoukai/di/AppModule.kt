package com.example.allen_jikoshoukai.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module

val AppModule = module {
    single { GsonBuilder().create() }
}