package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.Success
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionItemView
import org.junit.Assert
import org.junit.Test

class TransactionsStateUnitTest {
    @Test
    fun testTransactionsState() {
        val transactionView = TransactionItemView(
            id = "id",
            date = "date",
            amount = "amount",
            category = "category",
            description = "description"
        )
        val state = TransactionsState(transactions = Success(listOf(transactionView)))

        Assert.assertEquals(1, state.transactions.invoke()?.size)
    }
}