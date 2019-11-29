package com.example.allen_jikoshoukai.ui.architecture

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.allen_jikoshoukai.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

abstract class BaseFragment : Fragment()
{
    private val mainViewModel : MainViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        setBackPressedIntercept()
    }

    override fun onResume() {
        super.onResume()

        Timber.d("onResume")

        setBackPressedIntercept()
    }

    fun getSafeNavController(): NavController? {
       findNavController().currentDestination?.let {
           return findNavController()
       }?:

       return null
    }

    fun getSafeNavController(actionId : Int): NavController? {
        findNavController().currentDestination?.getAction(actionId)?.let {
            return findNavController()
        }

        return null
    }

    /**
     * Intercept Device's Back pressed event
     */
    private fun setBackPressedIntercept() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onOptionItemBackClicked(null)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    /**
     * Overiride to Handle Back button click
     */
    abstract fun onOptionItemBackClicked(item: MenuItem?)


    protected fun getMainVM() : MainViewModel {
        return mainViewModel
    }
}