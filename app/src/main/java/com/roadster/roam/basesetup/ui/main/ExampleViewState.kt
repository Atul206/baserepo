package com.roadster.roam.basesetup.ui.main

import com.roadster.roam.basesetup.navigation.ViewState

sealed class ExampleViewState: ViewState {
    object success:ExampleViewState()
}