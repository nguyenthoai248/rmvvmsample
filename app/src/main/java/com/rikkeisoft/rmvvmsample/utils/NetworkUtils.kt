package com.rikkeisoft.rmvvmsample.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
object NetworkUtils {

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
        return false
    }
}// This class is not publicly instantiable
