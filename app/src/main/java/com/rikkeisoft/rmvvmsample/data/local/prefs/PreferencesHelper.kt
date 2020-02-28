package com.rikkeisoft.rmvvmsample.data.local.prefs

import com.rikkeisoft.rmvvmsample.data.DataManager

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
interface PreferencesHelper {

    var accessToken: String

    var currentUserEmail: String

    var currentUserId: Long?

    val currentUserLoggedInMode: Int

    var currentUserName: String

    var currentUserProfilePicUrl: String

    fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode)
}