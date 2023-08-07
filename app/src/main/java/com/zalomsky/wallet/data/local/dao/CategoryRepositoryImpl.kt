package com.zalomsky.wallet.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zalomsky.wallet.domain.model.Category

@Dao
interface CategoryRepositoryImpl {

    @Insert
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<Category>

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM category WHERE id=:categoryId")
    suspend fun getCategoryById(categoryId: Long): Category
}