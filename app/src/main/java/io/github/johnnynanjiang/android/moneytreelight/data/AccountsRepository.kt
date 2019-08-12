package io.github.johnnynanjiang.android.moneytreelight.data

import io.reactivex.Observable
import retrofit2.http.GET

interface AccountsRepository {
    @GET("master/app/src/main/assets/json/accounts.json")
    fun getAccounts(): Observable<Accounts>
}