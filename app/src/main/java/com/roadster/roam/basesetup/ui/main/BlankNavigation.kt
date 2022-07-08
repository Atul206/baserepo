package com.roadster.roam.basesetup.ui.main

import com.roadster.roam.basesetup.navigation.Navigation

sealed class BlankNavigation: Navigation {
    object mainScreen:BlankNavigation()
}