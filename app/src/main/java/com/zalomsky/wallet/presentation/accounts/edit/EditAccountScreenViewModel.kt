package com.zalomsky.wallet.presentation.accounts.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.usecase.DeleteAccountUseCase
import com.zalomsky.wallet.domain.usecase.GetAccountByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAccountScreenViewModel @Inject constructor(

    private val getAccountByIdUseCase: GetAccountByIdUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
): ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account>
        get() = _account

    fun getAccountById(id: Long){
        viewModelScope.launch {
            getAccountByIdUseCase.invoke(id = id).let {
                _account.postValue(it)
            }
        }
    }

    fun deleteAccounts(onSuccess: () -> Unit){
        viewModelScope.launch {
            account.value?.let {
                deleteAccountUseCase.invoke(account = it)
                onSuccess()
            }
        }
    }
}