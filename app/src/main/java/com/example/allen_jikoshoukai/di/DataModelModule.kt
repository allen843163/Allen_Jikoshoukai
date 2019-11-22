package com.example.allen_jikoshoukai.di

import com.example.allen_jikoshoukai.MainDataModel
import com.example.allen_jikoshoukai.MainViewModel
import com.example.allen_jikoshoukai.ui.gallery.GalleryViewModel
import com.example.allen_jikoshoukai.ui.launch.LaunchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val DataModelModule = module {
    single {
        MainDataModel()
    }
}