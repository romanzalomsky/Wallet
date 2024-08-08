package com.zalomsky.wallet.presentation.accounts.update

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.AccountEntity
import com.zalomsky.wallet.domain.usecase.account.DeleteAccountUseCase
import com.zalomsky.wallet.domain.usecase.account.GetAccountByIdUseCase
import com.zalomsky.wallet.domain.usecase.account.UpdateAccountUseCase
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAccountScreenViewModel @Inject constructor(
    private val getAccountByIdUseCase: GetAccountByIdUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    fun loadAccount(id: Long) {
        viewModelScope.launch {
            getAccountByIdUseCase(id).collectLatest { result ->
                result
                    .onSuccess { account ->
                        _uiState.update { currentState ->
                            currentState.copy(accountEntity = account)
                        }
                    }.onFailure {
                        // todo: show message
                    }
            }
        }
    }

    fun onNameChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(accountEntity = currentState.accountEntity.copy(name = newValue))
        }
    }

    fun onDescriptionChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(accountEntity = currentState.accountEntity.copy(description = newValue))
        }
    }

    fun onBalanceChange(newValue: Double) {
        _uiState.update { currentState ->
            currentState.copy(accountEntity = currentState.accountEntity.copy(balance = newValue))
        }
    }

    fun onTargetChange(newValue: Double) {
        _uiState.update { currentState ->
            currentState.copy(accountEntity = currentState.accountEntity.copy(target = newValue))
        }
    }

    fun deleteAccounts(accountEntity: AccountEntity, onSuccess: () -> Unit) {
        viewModelScope.launch {
            deleteAccountUseCase(accountEntity)
                .onSuccess {
                    onSuccess()
                }
                .onFailure {
                    // TODO: show message
                }
        }
    }

    fun updateAccount(onAccountUpdated: () -> Unit) {
        viewModelScope.launch {
            updateAccountUseCase(uiState.value.accountEntity)
                .onSuccess {
                    onAccountUpdated()
                }
                .onFailure {
                    Log.d("Oshibka", "ERROR")
                }
        }
    }

    fun onEvent(event: AccountEvent) {
        when(event){
            is AccountEvent.Update -> updateAccount(event.onUpdated)
            is AccountEvent.Load -> loadAccount(event.accountId)
        }
    }
}

sealed interface AccountEvent {
    data class Update(val onUpdated: () -> Unit) : AccountEvent
    data class Load(val accountId: Long) : AccountEvent
}