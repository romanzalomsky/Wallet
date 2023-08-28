package com.zalomsky.wallet.presentation

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavHostController
import com.zalomsky.wallet.presentation.common.color.aqua
import com.zalomsky.wallet.presentation.common.color.black
import com.zalomsky.wallet.presentation.common.color.blue
import com.zalomsky.wallet.presentation.common.color.deepSkyBlue
import com.zalomsky.wallet.presentation.common.color.gold
import com.zalomsky.wallet.presentation.common.color.green
import com.zalomsky.wallet.presentation.common.color.grey
import com.zalomsky.wallet.presentation.common.color.lightCoral
import com.zalomsky.wallet.presentation.common.color.lime
import com.zalomsky.wallet.presentation.common.color.magenta
import com.zalomsky.wallet.presentation.common.color.orange
import com.zalomsky.wallet.presentation.common.color.pink
import com.zalomsky.wallet.presentation.common.color.purple
import com.zalomsky.wallet.presentation.common.color.red
import com.zalomsky.wallet.presentation.common.color.yellow
import com.zalomsky.wallet.presentation.common.icons.cardIcon
import com.zalomsky.wallet.presentation.common.icons.cardIcon2
import com.zalomsky.wallet.presentation.common.icons.cardIcon3
import com.zalomsky.wallet.presentation.common.icons.cardIcon4
import com.zalomsky.wallet.presentation.common.icons.cardIcon5
import com.zalomsky.wallet.presentation.common.icons.cashIcon
import com.zalomsky.wallet.presentation.common.icons.cashIcon2
import com.zalomsky.wallet.presentation.common.icons.cashIcon3
import com.zalomsky.wallet.presentation.common.icons.cashIcon4
import com.zalomsky.wallet.presentation.common.icons.cashIcon5
import com.zalomsky.wallet.presentation.common.icons.cashIcon6
import com.zalomsky.wallet.presentation.common.icons.familyCategoryIcon
import com.zalomsky.wallet.presentation.common.icons.groceriesCategoryIcon
import com.zalomsky.wallet.presentation.common.icons.healthCategoryIcon
import com.zalomsky.wallet.presentation.common.icons.restaurantCategoryIcon
import com.zalomsky.wallet.presentation.common.icons.shoppingCategoryIcon
import com.zalomsky.wallet.presentation.common.icons.sportCategoryIcon
import com.zalomsky.wallet.presentation.common.icons.transportCategoryIcon

class AppState(scaffoldState: ScaffoldState, navController: NavHostController) {

    companion object {

        const val accountsScreenTitle = "Accounts"
        const val newAccountScreenTitle = "new account"
        const val editAccountScreenTitle = "edit account"

        const val categoriesScreenTitle = "Categories"
        const val addCategoryScreenTitle = "add category"

    }

}

val listOfAccountsIcons = listOf(cardIcon, cardIcon2, cardIcon3,
                        cardIcon4, cardIcon5, cashIcon,
                        cashIcon2, cashIcon3, cashIcon4, cashIcon5, cashIcon6)

val listOfColors = listOf(grey, blue, red, yellow, green,
                          purple, aqua, black, pink, gold,
                          orange, lime, magenta, deepSkyBlue, lightCoral)

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