package com.zalomsky.wallet.presentation.accounts.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.usecase.AddAccountUseCase
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAccountScreenViewModel @Inject constructor(
    private val addAccountUseCase: AddAccountUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

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

    fun onIconChange(newValue: Int) { // delete unused functions
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(icon = newValue))
        }
    }

    fun onIconColor(newValue: Int) {
        _uiState.update { currentState ->
            currentState.copy(account = currentState.account.copy(iconColor = newValue))
        }
    }

    fun addAccount(onSuccess: () -> Unit) {
        viewModelScope.launch {
            addAccountUseCase(uiState.value.account)
                .onSuccess {
                    onSuccess()
                }
                .onFailure {
                    // todo: show toast/snackbar
                }
        }
    }

}
