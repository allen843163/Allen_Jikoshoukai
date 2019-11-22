package com.example.allen_jikoshoukai

import android.app.Application
import com.example.allen_jikoshoukai.di.AppModule
import com.example.allen_jikoshoukai.di.DataModelModule
import com.example.allen_jikoshoukai.di.ViewBindingModule
import com.example.allen_jikoshoukai.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        if(GlobalContext.getOrNull() == null){
            startKoin {
                androidLogger()
                androidContext(this@App)
                modules(listOf(AppModule, ViewModelModule, ViewBindingModule, DataModelModule))
            }
        }
    }
}