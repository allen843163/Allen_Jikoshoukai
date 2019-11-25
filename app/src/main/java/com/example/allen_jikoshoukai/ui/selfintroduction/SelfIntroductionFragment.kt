package com.example.allen_jikoshoukai.ui.selfintroduction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.allen_jikoshoukai.databinding.FragmentSelfIntroductionBinding
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment

class SelfIntroductionFragment : BaseFragment() {
    private lateinit var selfIntroductionViewModel: SelfIntroductionViewModel

    private lateinit var binding : FragmentSelfIntroductionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelfIntroductionBinding.inflate(inflater)

        selfIntroductionViewModel =
            ViewModelProviders.of(this).get(SelfIntroductionViewModel::class.java)

        binding.mainVM = getMainVM()

        return binding.root
    }
}