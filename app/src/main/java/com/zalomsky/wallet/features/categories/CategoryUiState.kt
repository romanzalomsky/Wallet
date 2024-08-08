package com.zalomsky.wallet.features.categories

import com.zalomsky.wallet.domain.model.Category

data class CategoryUiState(
    val category: Category = Category.defaultInstance()
)