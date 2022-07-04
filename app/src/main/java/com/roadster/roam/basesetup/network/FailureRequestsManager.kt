package com.roadster.roam.basesetup.network

interface FailureRequestsManager {

    val serviceUnavailableFailureRetrier: FailureRequestsRetrier
}