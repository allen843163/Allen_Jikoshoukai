package com.example.allen_jikoshoukai.ui.interests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintAttribute
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.allen_jikoshoukai.BR
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.FragmentHobbiesBinding
import com.example.allen_jikoshoukai.databinding.FragmentInterestsBinding
import com.example.allen_jikoshoukai.remote.model.Hobby
import com.example.allen_jikoshoukai.remote.model.Interest
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment

class InterestsFragment : BaseFragment() {
    private lateinit var binding : FragmentInterestsBinding

    var interestAdapter = object : MySimpleAdapter<Interest>(
        mutableListOf()
        , BR.interestData
        , R.layout.lv_item_interests) {

        override fun onViewItemSetting(holder: BaseViewHolder, view: View, item: Interest, position: Int) {
            super.onViewItemSetting(holder, view, item, position)

            holder.setIsRecyclable(false)

            var layoutMotion = view.findViewById<MotionLayout>(R.id.layout_content)

            var endConstraint = layoutMotion.getConstraintSet(R.id.end)
                .getConstraint(R.id.pg_level)

            endConstraint.mCustomConstraints.put("Progress", ConstraintAttribute("Progress", ConstraintAttribute.AttributeType.INT_TYPE, item.Level))

            layoutMotion.transitionToEnd()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInterestsBinding.inflate(inflater)

        binding.lvInterests.adapter = interestAdapter

        getMainVM().languageIndex.observe(this, Observer {
            getMainVM().getIntroductionRes.get()?.Language?.get(it)?.Interests?.let { interests ->
                interestAdapter.getData().clear()

                interestAdapter.getData().addAll(interests)

                interestAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        getSafeNavController()?.navigate(R.id.nav_self_introduction)
    }
}