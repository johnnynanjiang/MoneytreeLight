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
    val accounts: List<Account> = listOf(
        Account(
            name = "name1",
            id = "id1",
            institution = "institution1",
            currency = "JPY",
            current_balance = BigDecimal("100.00"),
            current_balance_in_base = BigDecimal("100.00")
        ),
        Account(
            name = "name2",
            id = "id2",
            institution = "institution2",
            currency = "CNY",
            current_balance = BigDecimal("200.00"),
            current_balance_in_base = BigDecimal("200.00")
        ),
        Account(
            name = "name3",
            id = "id3",
            institution = "institution3",
            currency = "AUD",
            current_balance = BigDecimal("300.00"),
            current_balance_in_base = BigDecimal("300.00")
        )
    )
) : MvRxState

class AccountsViewModel(state: AccountsState) : BaseMvRxViewModel<AccountsState>(state, debugMode = true)