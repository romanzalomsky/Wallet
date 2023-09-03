package com.zalomsky.wallet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zalomsky.wallet.presentation.listOfAccountsIcons
import com.zalomsky.wallet.presentation.listOfColors

@Entity
data class Account(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val balance: Double,
    val description: String,

    @ColumnInfo(name = "target", defaultValue = "1")
    val target: Double,

    @ColumnInfo(name = "type", defaultValue = "1")
    var type: String,

    @ColumnInfo(name = "icon")
    val icon: Int,
    val iconColor: Int
){
    companion object {

        fun defaultInstance() = Account (
            name = "",
            description = "",
            balance = 0.0,
            target = 0.0,
            icon = listOfAccountsIcons.random(),
            type = AccountType.REGULAR,
            iconColor = listOfColors.random()
        )
    }

}

