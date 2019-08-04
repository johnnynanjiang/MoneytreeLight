package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.app.MTLApplication
import java.math.BigDecimal
import io.github.johnnynanjiang.android.moneytreelight.data.AccountsRepository
import io.reactivex.schedulers.Schedulers

data class Account(
    val id: String,
    val name: String,
    val institution: String,
    val currency: String,
    val current_balance: BigDecimal,
    val current_balance_in_base: BigDecimal
)

data class AccountsState(val accounts: Async<List<Account>> = Uninitialized) : MvRxState

class AccountsViewModel(state: AccountsState, private val accountsRepository: AccountsRepository) :
    BaseMvRxViewModel<AccountsState>(state, debugMode = true) {

    init {
        getAccounts()
    }

    companion object : MvRxViewModelFactory<AccountsViewModel, AccountsState> {
        @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: AccountsState): AccountsViewModel? {
            return AccountsViewModel(state, viewModelContext.app<MTLApplication>().localAccountsRepository)
        }
    }

    private fun getAccounts() = accountsRepository.getAccounts()
        .subscribeOn(Schedulers.io())
        .execute { copy(accounts = it) }
}