package com.zalomsky.wallet.domain.usecase

import com.zalomsky.wallet.domain.repository.CategoryRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke() = categoryRepository.getAllCategories()
}