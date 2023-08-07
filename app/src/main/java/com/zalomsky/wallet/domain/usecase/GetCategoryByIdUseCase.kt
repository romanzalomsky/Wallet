package com.zalomsky.wallet.domain.usecase

import com.zalomsky.wallet.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoryByIdUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(id: Long) = categoryRepository.getCategoryById(id = id)
}