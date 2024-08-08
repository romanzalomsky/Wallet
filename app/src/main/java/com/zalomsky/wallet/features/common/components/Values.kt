package com.zalomsky.wallet.features.common.components

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.features.common.color.aqua
import com.zalomsky.wallet.features.common.color.black
import com.zalomsky.wallet.features.common.color.blue
import com.zalomsky.wallet.features.common.color.deepSkyBlue
import com.zalomsky.wallet.features.common.color.gold
import com.zalomsky.wallet.features.common.color.green
import com.zalomsky.wallet.features.common.color.grey
import com.zalomsky.wallet.features.common.color.lightCoral
import com.zalomsky.wallet.features.common.color.lime
import com.zalomsky.wallet.features.common.color.magenta
import com.zalomsky.wallet.features.common.color.orange
import com.zalomsky.wallet.features.common.color.pink
import com.zalomsky.wallet.features.common.color.purple
import com.zalomsky.wallet.features.common.color.red
import com.zalomsky.wallet.features.common.color.yellow
import com.zalomsky.wallet.features.common.icons.cardIcon
import com.zalomsky.wallet.features.common.icons.cardIcon1
import com.zalomsky.wallet.features.common.icons.cardIcon2
import com.zalomsky.wallet.features.common.icons.cardIcon3
import com.zalomsky.wallet.features.common.icons.familyCategoryIcon
import com.zalomsky.wallet.features.common.icons.groceriesCategoryIcon
import com.zalomsky.wallet.features.common.icons.healthCategoryIcon
import com.zalomsky.wallet.features.common.icons.restaurantCategoryIcon
import com.zalomsky.wallet.features.common.icons.shoppingCategoryIcon
import com.zalomsky.wallet.features.common.icons.sportCategoryIcon
import com.zalomsky.wallet.features.common.icons.transportCategoryIcon

val fontSize: TextUnit = 15.sp
val topAppBarFontSize: TextUnit = 20.sp
val horizontalPaddingSize: Dp = 15.dp

val listOfAccountsIcons = listOf(cardIcon, cardIcon1, cardIcon2, cardIcon3)

val listOfColors = listOf(
    grey, blue, red, yellow, green,
    purple, aqua, black, pink, gold,
    orange, lime, magenta, deepSkyBlue, lightCoral
)

val listOfCategoryIcons = listOf(
    transportCategoryIcon, groceriesCategoryIcon, healthCategoryIcon,
    shoppingCategoryIcon, familyCategoryIcon, restaurantCategoryIcon,
    sportCategoryIcon
)

val dollar = "$"
val euro = "E"
val ruble = "Rus"
val belRub = "Br"

val listOfValues = listOf(dollar, euro, ruble, belRub)