package com.example.allen_jikoshoukai.ui.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.databinding.FragmentLaunchBinding
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LaunchFragment : BaseFragment() {
    private val launchViewModel : LaunchViewModel by viewModel()

    private lateinit var binding : FragmentLaunchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel.actionBarVisibilityController.value = false

        binding = FragmentLaunchBinding.inflate(inflater)

        launchViewModel.remoteGetIntroduction()
        
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }
}