package com.zalomsky.wallet.domain.repository

import com.zalomsky.wallet.data.local.dao.CategoryRepositoryImpl
import com.zalomsky.wallet.domain.model.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryRepositoryImpl: CategoryRepositoryImpl
) {
    suspend fun getAllCategories(): List<Category> = categoryRepositoryImpl.getAllCategories()
    suspend fun insertCategory(category: Category) = categoryRepositoryImpl.insertCategory(category = category)
    suspend fun deleteCategory(category: Category) = categoryRepositoryImpl.deleteCategory(category = category)
    suspend fun getCategoryById(id: Long) = categoryRepositoryImpl.getCategoryById(categoryId = id)
    suspend fun updateCategory(category: Category) = categoryRepositoryImpl.updateCategory(category = category)
}