package com.zalomsky.wallet.presentation.categories

import com.zalomsky.wallet.domain.model.Category

data class CategoryUiState(
    val category: Category = Category.defaultInstance()
)