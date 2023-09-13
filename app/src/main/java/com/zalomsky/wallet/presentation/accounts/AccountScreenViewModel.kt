package com.zalomsky.wallet.presentation.accounts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.usecase.AddAccountUseCase
import com.zalomsky.wallet.domain.usecase.DeleteAccountUseCase
import com.zalomsky.wallet.domain.usecase.GetAccountByIdUseCase
import com.zalomsky.wallet.domain.usecase.GetAllAccountsUseCase
import com.zalomsky.wallet.domain.usecase.UpdateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel @Inject constructor(
    private val getAllAccountsUseCase: GetAllAccountsUseCase,
    private val addAccountUseCase: AddAccountUseCase,
    private val getAccountByIdUseCase: GetAccountByIdUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase
) : ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account>
        get() = _account

    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>>
        get() = _accounts

/*    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()*/

    var accountUiState by mutableStateOf(AccountUiState())
        private set

    fun getAllAccounts() {
        viewModelScope.launch {
            getAllAccountsUseCase.invoke().let {
                _accounts.postValue(it)
            }
        }
    }

    fun getAccountById(id: Long){
        viewModelScope.launch {
            getAccountByIdUseCase(id = id).let {
                _account.postValue(it)
            }
        }
    }

    fun saveAccount(onSuccess: () -> Unit){
        viewModelScope.launch {
            addAccountUseCase(accountUiState.accountDetails.toAccount())
            onSuccess()
        }
    }

    fun deleteAccount(onSuccess: () -> Unit){
        viewModelScope.launch {
            account.value?.let {
                deleteAccountUseCase.invoke(account = it)
                onSuccess()
            }
        }
    }

    fun updateAccount(onSuccess: () -> Unit){
        viewModelScope.launch {
            updateAccountUseCase(accountUiState.accountDetails.toAccount())
                .onSuccess {
                    onSuccess()
                }
                .onFailure {
                    //todo:
                }
        }
    }

    fun updateUiState(accountDetails: AccountDetails){
        accountUiState =
            AccountUiState(accountDetails = accountDetails)
    }
}
