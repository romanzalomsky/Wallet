package com.zalomsky.wallet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val icon: Int,
    val amount: Double
)