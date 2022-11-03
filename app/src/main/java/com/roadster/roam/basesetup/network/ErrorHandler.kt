package com.roadster.roam.basesetup.network

import com.roadster.roam.basesetup.network.BaseError
import retrofit2.Response

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity

    fun <T> getHttpErrors(errorResponse: Response<T>): ErrorEntity
}