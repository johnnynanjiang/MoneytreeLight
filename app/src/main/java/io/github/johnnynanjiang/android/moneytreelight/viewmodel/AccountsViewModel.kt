package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.app.MTLApplication
import io.github.johnnynanjiang.android.moneytreelight.data.AccountsRepository
import io.github.johnnynanjiang.android.moneytreelight.data.getTotalBalanceFromAccounts
import io.github.johnnynanjiang.android.moneytreelight.data.mapAccountsFromDataToDomain
import io.github.johnnynanjiang.android.moneytreelight.presentation.accounts.AccountView
import io.reactivex.schedulers.Schedulers

data class AccountsState(
    val accounts: Async<List<AccountView>> = Uninitialized,
    val totalBalance: String = ""
) : MvRxState

class AccountsViewModel(state: AccountsState, private val accountsRepository: AccountsRepository) :
    BaseMvRxViewModel<AccountsState>(state, debugMode = true) {

    companion object : MvRxViewModelFactory<AccountsViewModel, AccountsState> {
        @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: AccountsState): AccountsViewModel? {
            return AccountsViewModel(state, viewModelContext.app<MTLApplication>().localAccountsRepository)
        }
    }

    fun getAccounts() = accountsRepository.getAccounts()
        .subscribeOn(Schedulers.io())
        .map {
            setState { copy(totalBalance = getTotalBalanceFromAccounts(it)) }
            it
        }
        .map { mapAccountsFromDomainToPresentation(it) }
        .execute { copy(accounts = it) }
}