package com.rikkeisoft.rmvvmsample.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
@Entity(tableName = "users")
class User {

    @ColumnInfo(name = "created_at")
    var createdAt: String? = null

    @PrimaryKey
    var id: Long? = null

    var name: String? = null

    @ColumnInfo(name = "updated_at")
    var updatedAt: String? = null
}