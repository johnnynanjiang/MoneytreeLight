package io.github.johnnynanjiang.android.moneytreelight.data

import io.github.johnnynanjiang.android.moneytreelight.domain.Transaction
import io.github.johnnynanjiang.android.moneytreelight.domain.mapTransactionsFromJSONToDomain
import io.reactivex.Observable
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader

class LocalTransactionsRepository(private val localJSONFileLoader: LocalJSONFileLoader) : TransactionsRepository {
    companion object {
        private const val TRANSACTIONS_FILENAME = "json/transactions_2.json"
    }

    override fun getTransactionsForAccount() =
        Observable.fromCallable<List<Transaction>> {
            mapTransactionsFromJSONToDomain(localJSONFileLoader.getJSONObject(TRANSACTIONS_FILENAME))
        }
}