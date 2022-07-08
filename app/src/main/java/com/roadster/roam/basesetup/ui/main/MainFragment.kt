package com.roadster.roam.basesetup.ui.main

import com.roadster.roam.basesetup.R
import com.roadster.roam.basesetup.databinding.FragmentMainBinding
import com.roadster.roam.basesetup.extensions.ui.setOnClickListenerWithDebounce
import com.roadster.roam.basesetup.ui.main.base.BaseViewModelFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment
    : BaseViewModelFragment<FragmentMainBinding, MainViewModel, ExampleViewState, ExampleNavigation>() {

    override val viewModel: MainViewModel by viewModel()

    override val layout: Int
        get() = R.layout.fragment_main

    override fun setupViews() {
        binding.message.setOnClickListenerWithDebounce {
            viewModel.getDataCheck()
        }
    }

    override fun showErrorMsg(title: CharSequence, subtitle: CharSequence?) {}

    override fun showSuccessMsg(title: CharSequence, subtitle: CharSequence?) {}

    override fun setViewState(viewState: ExampleViewState) {
        when(viewState) {
           ExampleViewState.success -> {
               navigateTo(ExampleNavigation.nextScreen)
           }
            else -> {

            }
        }
    }

    override fun navigateTo(navigation: ExampleNavigation) {
        when(navigation)  {
            ExampleNavigation.nextScreen -> {
                navController.navigate(R.id.action_main_to_blank_fragment)
            }
            else -> {

            }
        }

    }


}