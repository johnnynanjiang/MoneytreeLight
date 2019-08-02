package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import java.math.BigDecimal
import io.github.johnnynanjiang.android.moneytreelight.data.AccountsRepository
import io.github.johnnynanjiang.android.moneytreelight.data.LocalAccountsRepository
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
            val accountsRepository = LocalAccountsRepository()
            return AccountsViewModel(state, accountsRepository)
        }
    }

    fun getAccounts() = accountsRepository.getAccounts()
        .subscribeOn(Schedulers.io())
        .execute { copy(accounts = it) }
}