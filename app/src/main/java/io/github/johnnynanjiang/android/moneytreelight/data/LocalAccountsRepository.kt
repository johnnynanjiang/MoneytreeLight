package io.github.johnnynanjiang.android.moneytreelight.data

import io.github.johnnynanjiang.android.moneytreelight.viewmodel.Account
import io.reactivex.Observable
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader
import org.json.JSONArray
import java.math.BigDecimal

class LocalAccountsRepository(private val localJSONFileLoader: LocalJSONFileLoader) : AccountsRepository {
    companion object {
        private const val ACCOUNTS_FILENAME = "json/accounts.json"
    }

    override fun getAccounts() =
        Observable.fromCallable<List<Account>> {
            val json = localJSONFileLoader.getJSONObject(ACCOUNTS_FILENAME)

            val jsonAccounts = json.get("accounts") as JSONArray

            val accounts = mutableListOf<Account>()

            for (i in 0..(jsonAccounts.length() - 1)) {
                val jsonAccount = jsonAccounts.getJSONObject(i)
                val account = with(jsonAccount) {
                    Account(
                        id = get("id").toString(),
                        name = get("name").toString(),
                        institution = get("institution").toString(),
                        currency = get("currency").toString(),
                        current_balance = BigDecimal(get("current_balance").toString()),
                        current_balance_in_base = BigDecimal(get("current_balance_in_base").toString())
                    )
                }
                accounts.add(account)
            }

            accounts
        }
}