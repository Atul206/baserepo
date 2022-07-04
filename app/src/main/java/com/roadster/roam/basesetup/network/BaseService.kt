package com.roadster.roam.basesetup.network

interface BaseService {

    fun clearFailedServiceUnavailableRequests()

    fun retryFailedServiceUnavailableRequests()
}
