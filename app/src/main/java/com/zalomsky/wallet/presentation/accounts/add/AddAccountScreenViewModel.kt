package com.zalomsky.wallet.presentation.accounts.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Account
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
): ViewModel() {

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    fun onNameChange(newValue: String){
        _uiState.update { currentState ->
            currentState.copy(name = newValue)
        }
    }

    fun onDescriptionChange(newValue: String){
        _uiState.update { currentState ->
            currentState.copy(description = newValue)
        }
    }

    fun onBalanceChange(newValue: Double){
        _uiState.update { currentState ->
            currentState.copy(balance = newValue)
        }
    }

    fun onIconChange(newValue: Int){
        _uiState.update { currentState ->
            currentState.copy(icon = newValue)
        }
    }

    fun onIconColor(newValue: Int){
        _uiState.update { currentState ->
            currentState.copy(iconColor = newValue)
        }
    }

    fun addAccount(account: Account, onSuccess: () -> Unit){
        viewModelScope.launch {
            addAccountUseCase.invoke(account = account)
            onSuccess()
        }
    }
}