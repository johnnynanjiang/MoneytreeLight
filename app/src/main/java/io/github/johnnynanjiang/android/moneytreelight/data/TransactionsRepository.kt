package io.github.johnnynanjiang.android.moneytreelight.data

import io.github.johnnynanjiang.android.moneytreelight.viewmodel.Transaction
import io.reactivex.Observable

interface TransactionsRepository {
    fun getTransactionsForAccount(): Observable<List<Transaction>>
}