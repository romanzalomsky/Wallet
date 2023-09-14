package com.zalomsky.wallet.domain.validator

import com.zalomsky.wallet.domain.model.Category
import javax.inject.Inject

class CategoryValidator @Inject constructor() {

    operator fun invoke(category: Category): Result<Unit> =
        when {
            category.name.isBlank() -> Result.failure(Exception("Name should't be blank!!!"))
            else -> Result.success(Unit)
        }
}