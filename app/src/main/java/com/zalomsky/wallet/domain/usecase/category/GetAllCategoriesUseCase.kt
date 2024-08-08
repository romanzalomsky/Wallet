package com.zalomsky.wallet.domain.usecase.category

import com.zalomsky.wallet.domain.repository.CategoryRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke() = categoryRepository.getAllCategories()
}