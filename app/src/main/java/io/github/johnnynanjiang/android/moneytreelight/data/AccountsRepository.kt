package io.github.johnnynanjiang.android.moneytreelight.data

import io.reactivex.Observable
import org.json.JSONObject

interface AccountsRepository {
    fun getAccounts(): Observable<JSONObject>
}