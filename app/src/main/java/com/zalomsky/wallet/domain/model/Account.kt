package com.zalomsky.wallet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zalomsky.wallet.presentation.listOfAccountsIcons
import com.zalomsky.wallet.presentation.listOfColors

@Entity
data class Account(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var balance: Double,
    var description: String,

    @ColumnInfo(name = "target", defaultValue = "1")
    var target: Double,

    @ColumnInfo(name = "type", defaultValue = "1")
    var type: String,

    @ColumnInfo(name = "icon")
    var icon: Int,
    var iconColor: Int
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



