package com.zalomsky.wallet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zalomsky.wallet.presentation.listOfColors

@Entity
data class Account( // todo: сделать отдельную модель для бд (AccountEntity)

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val balance: Double,
    val description: String,

    @ColumnInfo(name = "target", defaultValue = "1")
    val target: Double,

    @ColumnInfo(name = "type", defaultValue = "1")
    var type: String,

    var icon: Int,
    var iconColor: Int
){
    companion object {

        fun defaultInstance() = Account (
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

