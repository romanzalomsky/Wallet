package com.zalomsky.wallet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zalomsky.wallet.presentation.common.color.white
import com.zalomsky.wallet.presentation.common.icons.cardIcon1

@Entity
data class Account(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val balance: Double,
    val description: String,

    @ColumnInfo(name = "icon")
    val icon: Int,
    val iconColor: Int

){
    companion object {

        fun defaultInstance() = Account (
            name = "name",
            description = "description",
            balance = 0.0,
            icon = cardIcon1,
            iconColor = white
        )
    }
}

