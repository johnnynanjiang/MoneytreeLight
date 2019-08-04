package io.github.johnnynanjiang.android.moneytreelight.data

import io.reactivex.Observable
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader
import io.github.johnnynanjiang.android.moneytreelight.viewmodel.Transaction
import org.json.JSONArray
import java.math.BigDecimal

class LocalTransactionsRepository(private val localJSONFileLoader: LocalJSONFileLoader) : TransactionsRepository {
    companion object {
        private const val TRANSACTIONS_FILENAME = "json/transactions_2.json"
    }

    override fun getTransactionsForAccount() =
        Observable.fromCallable<List<Transaction>> {
            val json = localJSONFileLoader.getJSONObject(TRANSACTIONS_FILENAME)

            val jsonTransactions = json.get("transactions") as JSONArray

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

            transactions
        }
}