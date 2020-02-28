package com.rikkeisoft.rmvvmsample.data.local.db

import com.rikkeisoft.rmvvmsample.data.model.db.User
import io.reactivex.Observable

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
interface DbHelper {
    abstract fun insertUser(user: User): Observable<Boolean>
}