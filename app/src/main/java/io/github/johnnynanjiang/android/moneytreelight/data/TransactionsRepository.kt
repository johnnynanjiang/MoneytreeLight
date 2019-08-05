package io.github.johnnynanjiang.android.moneytreelight.data

import io.reactivex.Observable
import org.json.JSONObject

interface TransactionsRepository {
    fun getTransactionsForAccount(): Observable<JSONObject>
}