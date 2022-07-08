package com.roadster.roam.basesetup.ui.main

import com.roadster.roam.basesetup.navigation.ViewState

sealed class BlankViewState: ViewState {
    object backPressed:BlankViewState()
}