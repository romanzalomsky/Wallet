package com.zalomsky.wallet.features.accounts.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.usecase.account.AddAccountUseCase
import com.zalomsky.wallet.features.accounts.AccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val addAccountUseCase: AddAccountUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

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

    fun addAccount(onSuccess: () -> Unit) {
        viewModelScope.launch {
            addAccountUseCase(uiState.value.accountEntity)
                .onSuccess {
                    onSuccess()
                }
                .onFailure {

                }
        }
    }
}
