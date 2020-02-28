package com.rikkeisoft.rmvvmsample.data

import android.content.Context
import com.google.gson.Gson
import com.rikkeisoft.rmvvmsample.data.local.db.DbHelper
import com.rikkeisoft.rmvvmsample.data.local.prefs.PreferencesHelper
import com.rikkeisoft.rmvvmsample.data.model.api.LoginRequest
import com.rikkeisoft.rmvvmsample.data.model.api.LoginResponse
import com.rikkeisoft.rmvvmsample.data.model.db.User
import com.rikkeisoft.rmvvmsample.data.remote.ApiHeader
import com.rikkeisoft.rmvvmsample.data.remote.ApiHelper
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
@Singleton
class AppDataManager @Inject
constructor(
    private val mContext: Context, private val mDbHelper: DbHelper, private val mPreferencesHelper:
    PreferencesHelper, private val mApiHelper: ApiHelper, private val mGson: Gson
) : DataManager {
    override fun updateUserInfo(
        accessToken: String,
        userId: Long?,
        loggedInMode: DataManager.LoggedInMode,
        userName: String,
        email: String,
        profilePicPath: String
    ) {
        this.accessToken = accessToken
        this.currentUserId = userId
        setCurrentUserLoggedInMode(loggedInMode)
        this.currentUserName = userName
        this.currentUserEmail = email
        this.currentUserProfilePicUrl = profilePicPath

        updateApiHeader(userId, accessToken)
    }

    override val apiHeader: ApiHeader
        get() = mApiHelper.apiHeader

    override fun insertUser(user: User): Observable<Boolean> {
        return mDbHelper.insertUser(user)
    }

    override var accessToken: String
        get() = mPreferencesHelper.accessToken
        set(accessToken) {
            mPreferencesHelper.accessToken = accessToken
            mApiHelper.apiHeader.protectedApiHeader.accessToken = accessToken
        }
    override var currentUserEmail: String
        get() = mPreferencesHelper.currentUserEmail
        set(currentUserEmail) {
            mPreferencesHelper.currentUserEmail = currentUserEmail
        }
    override var currentUserId: Long?
        get() = mPreferencesHelper.currentUserId
        set(userId) {
            mPreferencesHelper.currentUserId = userId
        }
    override val currentUserLoggedInMode: Int
        get() = mPreferencesHelper.currentUserLoggedInMode

    override fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode)
    }

    override var currentUserName: String
        get() = mPreferencesHelper.currentUserName
        set(currentUserName) {
            mPreferencesHelper.currentUserName = currentUserName
        }
    override var currentUserProfilePicUrl: String
        get() = mPreferencesHelper.currentUserProfilePicUrl
        set(profilePicUrl) {
            mPreferencesHelper.currentUserProfilePicUrl = profilePicUrl
        }

    override fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse> {
        return mApiHelper.doServerLoginApiCall(request)
    }

    fun updateApiHeader(userId: Long?, accessToken: String) {
        mApiHelper.apiHeader.protectedApiHeader.userId = userId
        mApiHelper.apiHeader.protectedApiHeader.accessToken = accessToken
    }

}