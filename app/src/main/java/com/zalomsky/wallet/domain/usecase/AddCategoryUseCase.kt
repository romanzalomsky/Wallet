package com.zalomsky.wallet.domain.usecase

import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.domain.repository.CategoryRepository
import com.zalomsky.wallet.domain.validator.CategoryValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val categoryValidator: CategoryValidator
) {
    suspend operator fun invoke(category: Category): Result<Unit> =
        withContext(Dispatchers.IO){
            categoryValidator.invoke(category).onFailure {
                return@withContext Result.failure(it)
            }

            val result = runCatching {
                categoryRepository.insertCategory(category = category)
            }

            result
        }
}