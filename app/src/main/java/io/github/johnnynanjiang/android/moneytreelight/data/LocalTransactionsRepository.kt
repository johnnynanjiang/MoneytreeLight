package io.github.johnnynanjiang.android.moneytreelight.data

import io.reactivex.Observable
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader
import org.json.JSONObject

class LocalTransactionsRepository(private val localJSONFileLoader: LocalJSONFileLoader) : TransactionsRepository {
    override fun getTransactionsForAccount(accountId: String) =
        Observable.fromCallable<JSONObject> {
            Thread.sleep(3000)
            localJSONFileLoader.getJSONObject("json/transactions_$accountId.json")
        }
}