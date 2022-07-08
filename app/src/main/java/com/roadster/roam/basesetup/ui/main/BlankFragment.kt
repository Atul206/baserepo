package com.roadster.roam.basesetup.ui.main

import com.roadster.roam.basesetup.R
import com.roadster.roam.basesetup.databinding.FragmentBlankBinding
import com.roadster.roam.basesetup.extensions.ui.setOnClickListenerWithDebounce
import com.roadster.roam.basesetup.ui.main.base.BaseViewModelFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlankFragment : BaseViewModelFragment<FragmentBlankBinding, BlankViewModel, BlankViewState, BlankNavigation>() {
    override val layout: Int
        get() = R.layout.fragment_blank

    override val viewModel: BlankViewModel by viewModel()

    override fun setupViews() {
        binding.message2.setOnClickListenerWithDebounce {
            viewModel.onBackPressed()
        }
    }

    override fun showErrorMsg(title: CharSequence, subtitle: CharSequence?) {

    }

    override fun showSuccessMsg(title: CharSequence, subtitle: CharSequence?) {

    }

    override fun setViewState(viewState: BlankViewState) {
        when(viewState) {
            BlankViewState.backPressed -> {
                navigateTo(BlankNavigation.mainScreen)
            }
        }
    }

    override fun navigateTo(navigation: BlankNavigation) {
        when(navigation) {
            BlankNavigation.mainScreen ->{
                activity?.onBackPressed()
                //navController.navigate(R.id.action_blank_to_main_fragment)
            }
        }
    }

}