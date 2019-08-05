package io.github.johnnynanjiang.android.moneytreelight.data

import io.reactivex.Observable
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader
import org.json.JSONObject

class LocalTransactionsRepository(private val localJSONFileLoader: LocalJSONFileLoader) : TransactionsRepository {
    companion object {
        private const val TRANSACTIONS_FILENAME = "json/transactions_2.json"
    }

    override fun getTransactionsForAccount() =
        Observable.fromCallable<JSONObject> { localJSONFileLoader.getJSONObject(TRANSACTIONS_FILENAME) }
}