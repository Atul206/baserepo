package com.roadster.roam.basesetup.network


class FailureRequestsManagerImpl
 constructor() : FailureRequestsManager {

    override val serviceUnavailableFailureRetrier: FailureRequestsRetrier
            by lazy { FailureRequestsRetrierImpl() }
}