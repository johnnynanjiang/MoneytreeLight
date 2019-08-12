package io.github.johnnynanjiang.android.moneytreelight.app

import android.app.Application
import io.github.johnnynanjiang.android.moneytreelight.data.LocalAccountsRepository
import io.github.johnnynanjiang.android.moneytreelight.data.LocalTransactionsRepository
import io.github.johnnynanjiang.android.moneytreelight.data.RemoteAccountsRepository
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader

class MTLApplication : Application() {
    private val localJSONFileLoader by lazy { LocalJSONFileLoader(applicationContext) }
    val localAccountsRepository by lazy { LocalAccountsRepository(localJSONFileLoader) }
    val localTransactionsRepository by lazy { LocalTransactionsRepository(localJSONFileLoader) }
    val remoteAccountsRepository by lazy {RemoteAccountsRepository.getInstance()}
}