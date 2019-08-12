package io.github.johnnynanjiang.android.moneytreelight.data

import io.reactivex.Observable

interface AccountsRepository {
    fun getAccounts(): Observable<List<Account>>
}