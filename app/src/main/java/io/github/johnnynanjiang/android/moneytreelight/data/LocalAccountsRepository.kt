package io.github.johnnynanjiang.android.moneytreelight.data

import io.github.johnnynanjiang.android.moneytreelight.viewmodel.Account
import io.reactivex.Observable
import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.math.BigDecimal

class LocalAccountsRepository(private val applicationContext: Context) : AccountsRepository {
    companion object {
        private const val ACCOUNTS_FILENAME = "json/accounts.json"
    }

    private fun getJSONObject(): JSONObject =
        JSONObject(loadJSONFile())

    private fun loadJSONFile(): String =
        try {
            applicationContext.assets.open(ACCOUNTS_FILENAME).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            throw e
        }

    override fun getAccounts() =
        Observable.fromCallable<List<Account>> {
            val json = getJSONObject()
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