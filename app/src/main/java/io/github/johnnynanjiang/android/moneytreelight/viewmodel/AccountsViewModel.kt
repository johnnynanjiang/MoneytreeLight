package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.app.MTLApplication
import io.github.johnnynanjiang.android.moneytreelight.data.AccountsRepository
import io.github.johnnynanjiang.android.moneytreelight.domain.mapAccountsFromDataToDomain
import io.github.johnnynanjiang.android.moneytreelight.view.accounts.AccountView
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

data class AccountsState(val accounts: Async<List<AccountView>> = Uninitialized) : MvRxState

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
        .map { mapAccountsFromDataToView(it) }
        .execute { copy(accounts = it) }

    private fun mapAccountsFromDataToView(jsonObject: JSONObject): List<AccountView> =
        mapAccountsFromDomainToView(
            mapAccountsFromDataToDomain(jsonObject)
        )
}