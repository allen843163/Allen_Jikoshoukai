package com.example.allen_jikoshoukai.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.allen_jikoshoukai.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.nav_header_main.view.*
import timber.log.Timber

@BindingAdapter("app:setHeader")

public fun NavigationView.setHeader(name: String) {
    Timber.d("header count : ")

    inflateHeaderView(R.layout.nav_header_main).tv_name.text = name
}
