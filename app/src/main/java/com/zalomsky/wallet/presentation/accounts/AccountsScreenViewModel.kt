package com.zalomsky.wallet.presentation.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.usecase.DeleteAccountUseCase
import com.zalomsky.wallet.domain.usecase.GetAllAccountsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsScreenViewModel @Inject constructor(
    private val getAllAccountsUseCase: GetAllAccountsUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {

    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>>
        get() = _accounts


    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account>
        get() = _account


    init {
        getAllAccounts()
    }

    fun getAllAccounts() {
        viewModelScope.launch {
            getAllAccountsUseCase.invoke().let {
                _accounts.postValue(it)
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