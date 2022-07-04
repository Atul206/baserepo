package com.roadster.roam.basesetup.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class CheckConnectivityState  constructor(private val connectivityManager: ConnectivityManager) :
    ConnectivityState {

    override fun hasConnection(): Boolean {
        val network = connectivityManager.activeNetwork
        val connection =
            connectivityManager.getNetworkCapabilities(network)
        return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        connection.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                )
    }
}