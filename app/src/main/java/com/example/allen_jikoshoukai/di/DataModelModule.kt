package com.example.allen_jikoshoukai.di

import com.example.allen_jikoshoukai.MainDataModel
import org.koin.dsl.module


val DataModelModule = module {
    single {
        MainDataModel()
    }
}