package com.zalomsky.wallet.presentation.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.domain.usecase.DeleteCategoryUseCase
import com.zalomsky.wallet.domain.usecase.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase
): ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()

    val categories: LiveData<List<Category>>
        get() = _categories

    private val _category = MutableLiveData<Category>()

    val category: LiveData<Category>
        get() = _category

    init {
        getAllCategories()
    }

    fun getAllCategories(){
        viewModelScope.launch {
            getAllCategoriesUseCase.invoke().let {
                _categories.postValue(it)
            }
        }
    }

    fun deleteCategories(onSuccess: () -> Unit){
        viewModelScope.launch {
            category.value?.let {
                deleteCategoryUseCase.invoke(category = it)
                onSuccess()
            }
        }
    }
}