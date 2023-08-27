package com.zalomsky.wallet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val icon: Int,

    @ColumnInfo(name = "circleColor", defaultValue = "1")
    val circleColor: Int,

    val amount: Double
)