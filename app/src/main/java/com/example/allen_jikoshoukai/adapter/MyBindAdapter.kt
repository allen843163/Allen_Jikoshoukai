package com.example.allen_jikoshoukai.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
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

@BindingAdapter("app:setHeightPercent")
public fun ImageView.setHeightPercent(percent : Float) {
    Timber.d("header count : ")

    var lp = layoutParams as ConstraintLayout.LayoutParams

    lp.matchConstraintPercentHeight = percent

    layoutParams = lp
}

@BindingAdapter("app:setVerticalBias")
public fun ImageView.setVerticalBias(bias : Float) {
    Timber.d("header count : ")

    var lp = layoutParams as ConstraintLayout.LayoutParams

    lp.verticalBias = bias

    layoutParams = lp
}

@BindingAdapter("app:setProgressWithMax")
public fun ProgressBar.setProgressWithMax(progress : Int) {
    Timber.d("header count : ")

    when(progress > max) {
        false -> {
            setProgress(progress)
        }
    }
}
