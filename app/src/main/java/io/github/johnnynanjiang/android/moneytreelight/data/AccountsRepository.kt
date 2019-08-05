package io.github.johnnynanjiang.android.moneytreelight.data

import io.github.johnnynanjiang.android.moneytreelight.domain.Account
import io.reactivex.Observable

interface AccountsRepository {
    fun getAccounts(): Observable<List<Account>>
}