package com.example.allen_jikoshoukai.ui.skills

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.allen_jikoshoukai.ui.architecture.BaseViewModel

class SkillsViewModel(application: Application) : BaseViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}