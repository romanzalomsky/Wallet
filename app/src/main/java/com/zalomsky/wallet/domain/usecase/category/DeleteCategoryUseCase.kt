package com.zalomsky.wallet.domain.usecase.category

import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.domain.repository.CategoryRepository
import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(category: Category) = categoryRepository.deleteCategory(category = category)
}