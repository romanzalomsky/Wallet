package com.zalomsky.wallet.presentation.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.wallet.domain.usecase.account.GetAllAccountsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AccountsScreenViewModel @Inject constructor(
    getAllAccountsUseCase: GetAllAccountsUseCase
) : ViewModel() {

    val accounts = getAllAccountsUseCase.invoke()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1_000),
            initialValue = emptyList()
        )
}