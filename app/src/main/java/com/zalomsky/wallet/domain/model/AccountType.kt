package com.zalomsky.wallet.domain.model

class AccountType {
    companion object{
        const val REGULAR: String = "REGULAR"
        const val SAVING: String = "SAVING"
        const val DEBT: String = "DEBT"
    }
}

/*val accountType: Any
    get() = when {
       account.type == "REGULAR" -> AccountType.REGULAR
       account.type == "SAVING" -> AccountType.SAVING
       account.type == "DEBT" -> AccountType.DEBT
        else -> {}
    }*/
