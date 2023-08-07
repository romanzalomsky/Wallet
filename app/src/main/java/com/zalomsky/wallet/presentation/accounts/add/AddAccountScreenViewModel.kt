package com.zalomsky.wallet.presentation.accounts.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.usecase.AddAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAccountScreenViewModel @Inject constructor(
    private val addAccountUseCase: AddAccountUseCase
): ViewModel() {

    fun addAccount(account: Account, onSuccess: () -> Unit){
        viewModelScope.launch {
            addAccountUseCase.invoke(account = account)
            onSuccess()
        }
    }
}