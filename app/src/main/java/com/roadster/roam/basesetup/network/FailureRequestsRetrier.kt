package com.roadster.roam.basesetup.network

import retrofit2.Call
import retrofit2.Callback

interface FailureRequestsRetrier {

    fun <T> put(call: Call<T>, callback: Callback<T>)

    fun retry()

    fun clear()
}
