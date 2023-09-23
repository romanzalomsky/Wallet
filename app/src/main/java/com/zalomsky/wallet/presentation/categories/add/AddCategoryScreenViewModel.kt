package com.zalomsky.wallet.presentation.categories.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.domain.usecase.AddCategoryUseCase
import com.zalomsky.wallet.presentation.categories.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryScreenViewModel @Inject constructor(
    private val addCategoryUseCase: AddCategoryUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    private val _categories = MutableLiveData<List<Category>>()

    val categories: LiveData<List<Category>>
        get() = _categories

    private val _category = MutableLiveData<Category>()

    val category: LiveData<Category>
        get() = _category


    fun addCategory(onSuccess: () -> Unit){
        viewModelScope.launch {
            addCategoryUseCase(uiState.value.category)
            onSuccess()
        }
    }

    fun onNameChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(category = currentState.category.copy(name = newValue))
        }
    }

    fun onAmountChange(newValue: Double) {
        _uiState.update { currentState ->
            currentState.copy(category = currentState.category.copy(amount = newValue))
        }
    }

    fun onIconChange(newValue: Int) {
        _uiState.update { currentState ->
            currentState.copy(category = currentState.category.copy(icon = newValue))
        }
    }
}