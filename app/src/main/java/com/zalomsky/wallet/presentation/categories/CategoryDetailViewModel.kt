package com.zalomsky.wallet.presentation.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.domain.usecase.AddCategoryUseCase
import com.zalomsky.wallet.domain.usecase.DeleteCategoryUseCase
import com.zalomsky.wallet.domain.usecase.GetAllCategoriesUseCase
import com.zalomsky.wallet.domain.usecase.GetCategoryByIdUseCase
import com.zalomsky.wallet.domain.usecase.UpdateCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val addCategoryUseCase: AddCategoryUseCase
): ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    private val _category = MutableLiveData<Category>()
    private val _uiState = MutableStateFlow(CategoryUiState())

    val categories: LiveData<List<Category>>
        get() = _categories
    val category: LiveData<Category>
        get() = _category
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    fun getAllCategories(){
        viewModelScope.launch {
            getAllCategoriesUseCase.invoke().let {
                _categories.postValue(it)
            }
        }
    }

    fun addCategory(onSuccess: () -> Unit){
        viewModelScope.launch {
            addCategoryUseCase(uiState.value.category)
            onSuccess()
        }
    }

    fun getCategoryById(id: Long){
        viewModelScope.launch {
            getCategoryByIdUseCase.invoke(id = id).let {
                _category.postValue(it)
            }
        }
    }

    fun deleteCategory(onSuccess: () -> Unit){
        viewModelScope.launch {
            category.value?.let {
                deleteCategoryUseCase.invoke(category = it)
                onSuccess()
            }
        }
    }

    fun onNameChange(newValue: String){
        _uiState.update { currentState ->
            currentState.copy(category = currentState.category.copy(name = newValue))
        }
    }

    fun onAmountChange(newValue: Double){
        _uiState.update { currentState ->
            currentState.copy(category = currentState.category.copy(amount = newValue))
        }
    }

    fun updateCategory(onSuccess: () -> Unit){
        viewModelScope.launch {
            updateCategoryUseCase(uiState.value.category)
            onSuccess()
        }
    }
}