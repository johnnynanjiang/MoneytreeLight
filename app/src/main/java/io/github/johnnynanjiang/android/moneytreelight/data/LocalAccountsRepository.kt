package io.github.johnnynanjiang.android.moneytreelight.data

import io.github.johnnynanjiang.android.moneytreelight.viewmodel.Account
import io.reactivex.Observable
import java.math.BigDecimal

class LocalAccountsRepository : AccountsRepository {
    override fun getAccounts() =
        Observable.fromCallable<List<Account>> {
            Thread.sleep(3)

            listOf(
                Account(
                    name = "name1",
                    id = "id1",
                    institution = "institution1",
                    currency = "JPY",
                    current_balance = BigDecimal("100.00"),
                    current_balance_in_base = BigDecimal("100.00")
                ),
                Account(
                    name = "name2",
                    id = "id2",
                    institution = "institution2",
                    currency = "CNY",
                    current_balance = BigDecimal("200.00"),
                    current_balance_in_base = BigDecimal("200.00")
                ),
                Account(
                    name = "name3",
                    id = "id3",
                    institution = "institution3",
                    currency = "AUD",
                    current_balance = BigDecimal("300.00"),
                    current_balance_in_base = BigDecimal("300.00")
                )
            )
        }
}