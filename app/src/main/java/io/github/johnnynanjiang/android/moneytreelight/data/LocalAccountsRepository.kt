package io.github.johnnynanjiang.android.moneytreelight.data

import io.github.johnnynanjiang.android.moneytreelight.domain.Account
import io.github.johnnynanjiang.android.moneytreelight.domain.mapAccountsFromJSONToDomain
import io.reactivex.Observable
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader

class LocalAccountsRepository(private val localJSONFileLoader: LocalJSONFileLoader) : AccountsRepository {
    companion object {
        private const val ACCOUNTS_FILENAME = "json/accounts.json"
    }

    override fun getAccounts() =
        Observable.fromCallable<List<Account>> {
            mapAccountsFromJSONToDomain(localJSONFileLoader.getJSONObject(ACCOUNTS_FILENAME))
        }
}