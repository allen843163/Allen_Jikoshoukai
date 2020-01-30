package com.example.allen_jikoshoukai.di

import com.example.allen_jikoshoukai.MainViewModel
import com.example.allen_jikoshoukai.ui.skills.SkillsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
    }

    viewModel {
        SkillsViewModel(get())
    }

}