package com.zalomsky.wallet

import com.zalomsky.wallet.presentation.common.icons.accountsIcon
import com.zalomsky.wallet.presentation.common.icons.categoriesIcon
import com.zalomsky.wallet.presentation.common.icons.overviewIcon
import com.zalomsky.wallet.presentation.common.icons.transactionsIcon

sealed class MainDestinations {

}

interface BottomDestinations {
    val route: String
    val icon: Int
}
object Accounts : BottomDestinations{

    override val icon = accountsIcon
    override val route = "Accounts"
}
object Categories : BottomDestinations{

    override val icon = categoriesIcon
    override val route = "Categories"
}
object Overview : BottomDestinations{

    override val icon = overviewIcon
    override val route = "Overview"
}
object Transactions : BottomDestinations{

    override val icon = transactionsIcon
    override val route = "Transactions"
}

interface Destinations {
    val route: String
}

object Add : Destinations{
    override val route = "addScreen"
}

object Edit : Destinations{
    override val route = "editScreen"
}

object AddCategory : Destinations{
    override val route = "addCategoryScreen"
}

object EditCategory : Destinations{
    override val route = "editCategoryScreen"
}

val bottomBarScreens = listOf(
    Accounts,
    Categories,
    Transactions,
    Overview
)

