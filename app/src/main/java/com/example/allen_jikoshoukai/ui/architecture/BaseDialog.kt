package com.example.allen_jikoshoukai.ui.architecture

import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.allen_jikoshoukai.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseDialog : DialogFragment() {
    private val mainViewModel : MainViewModel by sharedViewModel()

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

    protected fun getMainVM() : MainViewModel {
        return mainViewModel
    }
}