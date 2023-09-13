package com.zalomsky.wallet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zalomsky.wallet.presentation.listOfCategoryIcons
import com.zalomsky.wallet.presentation.listOfColors

@Entity
data class Category(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val icon: Int,

    @ColumnInfo(name = "circleColor", defaultValue = "1")
    val circleColor: Int,

    val amount: Double
){

    companion object {
        fun defaultInstance() = Category(
            name = "",
            icon = listOfCategoryIcons.random(),
            circleColor = listOfColors.random(),
            amount = 0.0
        )
    }
}