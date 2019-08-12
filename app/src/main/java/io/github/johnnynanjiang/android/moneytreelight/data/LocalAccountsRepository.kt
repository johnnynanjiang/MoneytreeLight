package io.github.johnnynanjiang.android.moneytreelight.data

import io.reactivex.Observable
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader

class LocalAccountsRepository(private val localJSONFileLoader: LocalJSONFileLoader) : AccountsRepository {
    companion object {
        private const val ACCOUNTS_FILENAME = "json/accounts.json"
    }

    override fun getAccounts() =
        Observable.fromCallable<Accounts> {
            mapAccountsFromDataToDomain(localJSONFileLoader.getJSONObject(ACCOUNTS_FILENAME))
        }
}