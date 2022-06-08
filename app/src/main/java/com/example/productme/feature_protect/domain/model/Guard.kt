package com.example.productme.feature_protect.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.productme.feature_protect.util.Constants.GUARD_TABLE_NAME

@Entity(tableName = GUARD_TABLE_NAME)
data class Guard(
    val name: String,
    val phone: String,
    @PrimaryKey
    val gid: Int? = null,
)
