package com.roadster.roam.basesetup.module

import com.roadster.roam.basesetup.ui.main.BlankViewModel
import com.roadster.roam.basesetup.ui.main.ExampleUseCase
import com.roadster.roam.basesetup.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {

    single { ExampleUseCase() }
    viewModel { MainViewModel(get()) }
    viewModel { BlankViewModel() }
}