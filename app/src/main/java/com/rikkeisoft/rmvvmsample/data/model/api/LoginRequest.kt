package com.rikkeisoft.rmvvmsample.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
object LoginRequest {

    class ServerLoginRequest(@field:Expose
                             @field:SerializedName("email")
                             val email: String?, @field:Expose
                             @field:SerializedName("password")
                             val password: String?) {

        override fun equals(`object`: Any?): Boolean {
            if (this === `object`) {
                return true
            }
            if (`object` == null || javaClass != `object`.javaClass) {
                return false
            }

            val that = `object` as ServerLoginRequest?

            if (if (email != null) email != that!!.email else that!!.email != null) {
                return false
            }
            return if (password != null) password == that.password else that.password == null
        }

        override fun hashCode(): Int {
            var result = email?.hashCode() ?: 0
            result = 31 * result + (password?.hashCode() ?: 0)
            return result
        }
    }
}// This class is not publicly instantiable
