package io.github.johnnynanjiang.android.moneytreelight.domain

import org.json.JSONArray
import org.json.JSONObject
import java.math.BigDecimal

fun mapAccountsFromDataToDomain(jsonObject: JSONObject): List<Account> {
    val jsonAccounts = jsonObject.get("accounts") as JSONArray

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

    return accounts
}

fun mapTransactionsFromDataToDomain(jsonObject: JSONObject): List<Transaction> {
    val jsonTransactions = jsonObject.get("transactions") as JSONArray

    val transactions = mutableListOf<Transaction>()

    for (i in 0..(jsonTransactions.length() - 1)) {
        val jsonTransaction = jsonTransactions.getJSONObject(i)
        val transaction = with(jsonTransaction) {
            Transaction(
                id = get("id").toString(),
                account_id = get("account_id").toString(),
                amount = BigDecimal(get("amount").toString()),
                category_id = get("category_id").toString(),
                date = get("date").toString(),
                description = get("description").toString()
            )
        }
        transactions.add(transaction)
    }

    return transactions
}