package com.zalomsky.wallet

import com.zalomsky.wallet.presentation.common.icons.accountsIcon
import com.zalomsky.wallet.presentation.common.icons.categoriesIcon
import com.zalomsky.wallet.presentation.common.icons.hiddenListIcon
import com.zalomsky.wallet.presentation.common.icons.overviewIcon
import com.zalomsky.wallet.presentation.common.icons.transactionsIcon

object MainDestinations{
    const val START_SCREEN = "start_screen"
}

interface Destinations {
    val route: String
    val icon: Int
}
interface Dest {
    val route: String
}

object Splash {
    val route = "splash"
}

object Accounts : Destinations{

    override val icon = accountsIcon
    override val route = "accounts"
}

object Categories : Destinations{

    override val icon = categoriesIcon
    override val route = "categories"
}

object Overview : Destinations{

    override val icon = overviewIcon
    override val route = "overview"
}

object Transactions : Destinations{

    override val icon = transactionsIcon
    override val route = "transactions"
}

object HiddenList : Destinations{

    override val icon = hiddenListIcon
    override val route = "hiddenList"
}

object Add : Dest{
    override val route = "addScreen"
}

object Edit : Dest{
    override val route = "editScreen"
}

object AddCategory : Dest{
    override val route = "addCategoryScreen"
}

object EditCategory : Dest{
    override val route = "editCategoryScreen"
}

val bottomBarScreens = listOf(Accounts, Categories, Transactions, Overview)
val topBarScreens = listOf(HiddenList)



