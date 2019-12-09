package com.example.allen_jikoshoukai.ui.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.allen_jikoshoukai.BR

import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.FragmentSkillsBinding
import com.example.allen_jikoshoukai.remote.model.Skill
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import com.example.allen_jikoshoukai.ui.skills.category.CategoryFragment
import com.example.allen_jikoshoukai.ui.skills.detail.DetailFragment
import com.example.allen_jikoshoukai.ui.skills.skill.SkillFragment
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

class SkillsFragment : BaseFragment() {
    private val skillsViewModel : SkillsViewModel by sharedViewModel()

    lateinit var binding : FragmentSkillsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        binding = FragmentSkillsBinding.inflate(inflater)

        binding.vpSkills.isUserInputEnabled = false

        binding.vpSkills.adapter = fragmentManager?.let {
            SkillsAdapter(
                it,
                this.lifecycle,
                mutableListOf(SkillFragment(), CategoryFragment(), DetailFragment()))
        }

        getMainVM().languageIndex.observe(this, Observer { it ->
            getMainVM().getIntroductionRes.get()?.let { introduction ->
                skillsViewModel.skillIndex.value = -1
            }

        })

        skillsViewModel.skillIndex.observe(this, Observer {
            Timber.d("observe")

            when(it >= 0) {
                true -> {
                    binding.vpSkills.setCurrentItem(1, true)
                }
                false -> {
                    binding.vpSkills.setCurrentItem(0, true)
                }
            }
        })

        skillsViewModel.categoryIndex.observe(this, Observer {
            when(it >= 0) {
                true -> {
                    binding.vpSkills.setCurrentItem(2, true)
                }
                false -> {
                    skillsViewModel.skillIndex.value?.let {
                        when(it >= 0) {
                            true -> {
                                binding.vpSkills.setCurrentItem(1, true)
                            }
                            false -> {
                                binding.vpSkills.setCurrentItem(0, true)
                            }
                        }
                    }?:let {
                        binding.vpSkills.setCurrentItem(0, true)
                    }
                }
            }
        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        return
    }
}