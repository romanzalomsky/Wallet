package com.zalomsky.wallet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zalomsky.wallet.presentation.listOfColors

@Entity(tableName = "account_table")
data class AccountEntity(

    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val balance: Double,
    val description: String,
    val target: Double,
    var type: String,
    var icon: Int,
    var iconColor: Int
){

    companion object {

        fun defaultInstance() = AccountEntity(
            name = "",
            description = "",
            balance = 0.0,
            target = 0.0,
            icon = 2131099655,
            type = "",
            iconColor = listOfColors.random()
        )
    }
}

