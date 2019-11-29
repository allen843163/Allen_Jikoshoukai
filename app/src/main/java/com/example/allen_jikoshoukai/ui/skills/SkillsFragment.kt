package com.example.allen_jikoshoukai.ui.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.FragmentSkillsBinding
import com.example.allen_jikoshoukai.remote.model.Skill
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

class SkillsFragment : BaseFragment() {
    private val skillsViewModel : SkillsViewModel by viewModel()

    lateinit var binding : FragmentSkillsBinding

    var skillAdapter = MySimpleAdapter(
        mutableListOf<Skill>()
        , BR.skillData
        ,R.layout.lv_item_skills
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        binding = FragmentSkillsBinding.inflate(inflater)

        binding.mainVM = getMainVM()

        binding.lvSkills.adapter = skillAdapter

        getMainVM().skillData.observe(this, Observer {
            Timber.d("skillData Observer :".plus(it[0].Name))

            Timber.d("skillData Observer :".plus(skillAdapter.getData().size))

            skillAdapter.getData().clear()

            skillAdapter.getData().addAll(it)

            skillAdapter.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
       getSafeNavController()
           ?.navigate(R.id.nav_self_introduction)
    }
}