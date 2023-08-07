package com.zalomsky.wallet.presentation.categories.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.domain.usecase.DeleteCategoryUseCase
import com.zalomsky.wallet.domain.usecase.GetCategoryByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCategoryScreenViewModel @Inject constructor(

    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase
): ViewModel(){

    private val _category = MutableLiveData<Category>()

    val category: LiveData<Category>
        get() = _category

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
}