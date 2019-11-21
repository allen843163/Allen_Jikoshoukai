package com.example.allen_jikoshoukai

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val actionBarVisibilityController = MutableLiveData<Boolean>()

//    init {
//        actionBarVisibilityController.value = true
//    }
}