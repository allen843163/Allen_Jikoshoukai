package com.example.allen_jikoshoukai.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.allen_jikoshoukai.R
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

        @BindingAdapter("app:setHeader")

        public fun NavigationView.bindCurrency(name: String) {
            Timber.d("header count : ")
//
            inflateHeaderView(R.layout.nav_header_main)
//
//            Timber.d("header count : ".plus(view.headerCount))
        }
