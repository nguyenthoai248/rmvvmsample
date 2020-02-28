package com.rikkeisoft.rmvvmsample.data

import com.rikkeisoft.rmvvmsample.data.local.db.DbHelper
import com.rikkeisoft.rmvvmsample.data.local.prefs.PreferencesHelper
import com.rikkeisoft.rmvvmsample.data.remote.ApiHelper

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
interface DataManager : DbHelper, PreferencesHelper, ApiHelper {

    enum class LoggedInMode(val type: Int) {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        //        LOGGED_IN_MODE_GOOGLE(1),
//        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3)
    }

    fun updateUserInfo(
        accessToken: String,
        userId: Long?,
        loggedInMode: LoggedInMode,
        userName: String,
        email: String,
        profilePicPath: String
    )
}