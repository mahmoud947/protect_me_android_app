package com.example.productme.feature_protect.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.productme.feature_protect.util.Constants.GUARD_TABLE_NAME

@Entity(tableName = GUARD_TABLE_NAME, indices = [Index(value = ["name"], unique = true)])
data class Guard(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @PrimaryKey
    val gid: Int? = null,
)
