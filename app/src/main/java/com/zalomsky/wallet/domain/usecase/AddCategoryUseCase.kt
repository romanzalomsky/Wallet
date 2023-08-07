package com.zalomsky.wallet.domain.usecase

import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.domain.repository.CategoryRepository
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(category: Category) = categoryRepository.insertCategory(category = category)
}