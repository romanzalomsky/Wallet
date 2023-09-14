package com.zalomsky.wallet.presentation.accounts.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.usecase.DeleteAccountUseCase
import com.zalomsky.wallet.domain.usecase.GetAccountByIdUseCase
import com.zalomsky.wallet.domain.usecase.UpdateAccountUseCase
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAccountScreenViewModel @Inject constructor(
    private val getAccountByIdUseCase: GetAccountByIdUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase
) : ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account>
        get() = _account

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    fun getAccountById(id: Long) {
        viewModelScope.launch {
            getAccountByIdUseCase(id).let {
                _account.postValue(it) // todo: post когда с другого потока, _account.value = newValue когда с main потока
            }
        }
    }

    fun onNameChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(name = newValue))
        }
    }

    fun onDescriptionChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(description = newValue))
        }
    }

    fun onBalanceChange(newValue: Double) {
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(balance = newValue))
        }
    }

    fun onTargetChange(newValue: Double) {
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(target = newValue))
        }
    }

    fun deleteAccounts(onSuccess: () -> Unit) {
        viewModelScope.launch {
            account.value?.let {
                deleteAccountUseCase(account = it)
                onSuccess()
            }
        }
    }

    /*    fun updateAccounts(account: Account, onSuccess: () -> Unit){ todo: удалить закоменченое все если не надо, если надо - раскоментить
            viewModelScope.launch(Dispatchers.IO) {
                updateAccountUseCase.invoke(account = account)
                onSuccess()
            }
        }*/

    fun updateAccount(onAccountUpdated: () -> Unit) {
        viewModelScope.launch {
            updateAccountUseCase // todo: убрать !!
                .runCatching {
                    onAccountUpdated()
                }
        }
    }

}