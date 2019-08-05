package io.github.johnnynanjiang.android.moneytreelight.data

import io.reactivex.Observable
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader
import org.json.JSONObject

class LocalAccountsRepository(private val localJSONFileLoader: LocalJSONFileLoader) : AccountsRepository {
    companion object {
        private const val ACCOUNTS_FILENAME = "json/accounts.json"
    }

    override fun getAccounts() =
        Observable.fromCallable<JSONObject> { localJSONFileLoader.getJSONObject(ACCOUNTS_FILENAME) }
}