package io.github.johnnynanjiang.android.moneytreelight.app

import android.app.Application
import io.github.johnnynanjiang.android.moneytreelight.data.LocalAccountsRepository

class MTLApplication : Application() {
    val localAccountsRepository by lazy { LocalAccountsRepository(applicationContext) }
}