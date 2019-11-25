package com.example.allen_jikoshoukai.ui.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.allen_jikoshoukai.MainViewModel
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.databinding.FragmentLaunchBinding
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
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
        getMainVM().actionBarVisibilityController.value = false

        binding = FragmentLaunchBinding.inflate(inflater)

        getMainVM().remoteGetIntroduction(object : MainViewModel.GetIntroductionCallBack{
            override fun onSuccess() {
                findNavController().currentDestination
                    ?.getAction(R.id.action_nav_launch_to_nav_self_introduction)
                    ?.let { findNavController().navigate(R.id.action_nav_launch_to_nav_self_introduction) }
            }

            override fun onFailure() {
                this@LaunchFragment
                    .let {
                        Toast.makeText(it.context, "Fail to load Introduction.\n Please check network or format", Toast.LENGTH_LONG).show()
                    }
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
}