package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import java.math.BigDecimal
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState

data class Account(
    val id: String,
    val name: String,
    val institution: String,
    val currency: String,
    val current_balance: BigDecimal,
    val current_balance_in_base: BigDecimal
)

data class AccountsState(
    val accounts: List<Account> = listOf()
) : MvRxState

class AccountsViewModel(state: AccountsState) : BaseMvRxViewModel<AccountsState>(state, debugMode = true)