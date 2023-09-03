package com.zalomsky.wallet.presentation.accounts

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
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

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    fun getAccountById(id: Long){
        viewModelScope.launch {
            getAccountByIdUseCase(id = id).let {
                _account.postValue(it)
            }
        }
    }

    fun getAllAccounts() {
        viewModelScope.launch {
            getAllAccountsUseCase.invoke().let {
                _accounts.postValue(it)
            }
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

    fun addAccount(onSuccess: () -> Unit){
        viewModelScope.launch {
            addAccountUseCase(uiState.value.account)
            onSuccess()
        }
    }

    /*    fun updateAccounts(account: Account, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            updateAccountUseCase.invoke(account = account)
            onSuccess()
        }
    }*/

    fun updateAccount(onSuccess: () -> Unit){
        viewModelScope.launch {
            account.value?.let { updateAccountUseCase(it) }
            onSuccess()
        }
    }

    fun onNameChange(newValue: String){
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(name = newValue))
        }
    }

    fun onDescriptionChange(newValue: String){
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(description = newValue))
        }
    }

    fun onBalanceChange(newValue: Double){
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(balance = newValue))
        }
    }

    fun onTargetChange(newValue: Double){
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(target = newValue))
        }
    }

    fun onIconChange(newValue: Int){
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(icon = newValue))
        }
    }

    fun onIconColor(newValue: Int){
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(iconColor = newValue))
        }
    }
}