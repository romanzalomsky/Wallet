package com.zalomsky.wallet.domain.repository

import com.zalomsky.wallet.data.local.dao.CategoryDao
import com.zalomsky.wallet.domain.model.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun getAllCategories(): List<Category> = categoryDao.getAllCategories()
    suspend fun insertCategory(category: Category) = categoryDao.insertCategory(category = category)
    suspend fun deleteCategory(category: Category) = categoryDao.deleteCategory(category = category)
    suspend fun getCategoryById(id: Long) = categoryDao.getCategoryById(categoryId = id)
    suspend fun updateCategory(category: Category) = categoryDao.updateCategory(category = category)
}