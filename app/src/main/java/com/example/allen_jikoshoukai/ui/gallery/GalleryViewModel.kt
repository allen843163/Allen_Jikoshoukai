package com.example.allen_jikoshoukai.ui.gallery

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.allen_jikoshoukai.ui.architecture.BaseViewModel

class GalleryViewModel(application: Application) : BaseViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}