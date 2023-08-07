package com.zalomsky.wallet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val balance: Double,

    @ColumnInfo(name = "icon", defaultValue = "")
    val icon: Int,

    @ColumnInfo(name = "iconColor", defaultValue = "")
    val iconColor: Int


)

