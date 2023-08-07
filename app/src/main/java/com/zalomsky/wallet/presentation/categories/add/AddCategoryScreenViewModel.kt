package com.zalomsky.wallet.presentation.categories.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.domain.usecase.AddCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryScreenViewModel @Inject constructor(
    private val addCategoryUseCase: AddCategoryUseCase
): ViewModel() {

    fun addCategory(category: Category, onSuccess: () -> Unit){
        viewModelScope.launch {
            addCategoryUseCase.invoke(category = category)
            onSuccess()
        }
    }
}