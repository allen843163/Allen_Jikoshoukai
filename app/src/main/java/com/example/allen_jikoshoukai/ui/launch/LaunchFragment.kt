package com.example.allen_jikoshoukai.ui.launch

import android.os.Build
import android.os.Bundle
import android.text.style.TtsSpan
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.allen_jikoshoukai.BuildConfig
import com.example.allen_jikoshoukai.MainViewModel
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.databinding.FragmentLaunchBinding
import com.example.allen_jikoshoukai.remote.model.IntroductionRes
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.coroutines.suspendCoroutine

class LaunchFragment : BaseFragment() {
    private lateinit var binding : FragmentLaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        getMainVM().init()

        getMainVM().actionBarVisibilityController.value = false

        binding = FragmentLaunchBinding.inflate(inflater)

        getMainVM().remoteGetIntroduction().observe(this, Observer { result ->
            result.fold(
                onFailure = {throwable ->
                    this@LaunchFragment.let {
                        Toast.makeText(it.context, throwable.message, Toast.LENGTH_LONG).show()
                    }

                },
                onSuccess = {
                    getSafeNavController(R.id.action_nav_launch_to_nav_language_select)
                        ?.navigate(R.id.action_nav_launch_to_nav_language_select)
                }
            )
        })

        getMainVM().languageIndex.observe(this, Observer {
            Timber.d("languageIndex : ".plus(it))

            if(it >= 0) {
                binding.layoutLaunchMain.setTransitionListener(object : MotionLayout.TransitionListener{
                    override fun onTransitionTrigger(
                        p0: MotionLayout?,
                        p1: Int,
                        p2: Boolean,
                        p3: Float
                    ) {
                    }

                    override fun onTransitionChange(
                        p0: MotionLayout?,
                        p1: Int,
                        p2: Int,
                        p3: Float
                    ) {
                    }

                    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                        getSafeNavController()?.navigate(
                            R.id.nav_self_introduction
                            , null
                            , NavOptions.Builder()
                                .setLaunchSingleTop(true)
                                .setPopUpTo(R.id.nav_launch,true)
                                .build())
                    }

                    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                       binding.lavLoading.cancelAnimation()
                    }

                })

                binding.layoutLaunchMain.transitionToEnd()
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Timber.d("onDestroyView")

        getMainVM().actionBarVisibilityController.value = true
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        return
    }
}