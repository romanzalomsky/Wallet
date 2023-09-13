package com.zalomsky.wallet

import androidx.annotation.StringRes
import com.zalomsky.wallet.MainDestinations.HOME_ROUTE
import com.zalomsky.wallet.presentation.common.icons.accountsIcon
import com.zalomsky.wallet.presentation.common.icons.categoriesIcon
import com.zalomsky.wallet.presentation.common.icons.overviewIcon
import com.zalomsky.wallet.presentation.common.icons.transactionsIcon

object MainDestinations {

    const val HOME_ROUTE = "home"
    const val ACCOUNT_ROUTE = "account"

    const val ADD_ACCOUNT_ROUTE = "add_account"
    const val EDIT_ACCOUNT_ROUTE = "edit_account"

    const val ADD_CATEGORY_ROUTE = "add_category"
    const val EDIT_CATEGORY_ROUTE = "edit_category"

    const val DRAWER_ROUTE = "drawer"
}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: Int,
    val route: String
) {
    ACCOUNTS(
        R.string.accounts,
        accountsIcon,
        "$HOME_ROUTE/accounts"
    ),
    CATEGORIES(
        R.string.categories,
        categoriesIcon,
        "$HOME_ROUTE/categories"
    ),
    TRANSACTIONS(
        R.string.transactions,
        transactionsIcon,
        "$HOME_ROUTE/transactions"
    ),
    OVERVIEW(
        R.string.overview,
        overviewIcon,
        "$HOME_ROUTE/overview"
    )
}
