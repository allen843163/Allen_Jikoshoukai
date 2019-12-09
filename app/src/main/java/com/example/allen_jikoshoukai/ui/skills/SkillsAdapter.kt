package com.example.allen_jikoshoukai.ui.skills

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SkillsAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val fragmentList : MutableList<Fragment>) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}