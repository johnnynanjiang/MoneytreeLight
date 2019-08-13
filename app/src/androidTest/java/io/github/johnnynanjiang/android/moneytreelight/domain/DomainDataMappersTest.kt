package io.github.johnnynanjiang.android.moneytreelight.domain

import android.content.Context
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import io.github.johnnynanjiang.android.moneytreelight.data.getTotalBalanceFromAccounts
import io.github.johnnynanjiang.android.moneytreelight.data.mapAccountsFromDataToDomain
import io.github.johnnynanjiang.android.moneytreelight.data.mapTransactionsFromDataToDomain
import io.github.johnnynanjiang.android.moneytreelight.util.LocalJSONFileLoader

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.math.BigDecimal

@RunWith(AndroidJUnit4::class)
class DomainDataMappersTest {
    private lateinit var appContext: Context

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getTargetContext()
    }

    @Test
    fun shouldMapAccountsFromJSONDataToDomainData() {
        val jsonObject = LocalJSONFileLoader(appContext).getJSONObject("json/accounts.json")
        val accounts = mapAccountsFromDataToDomain(jsonObject)

        assertEquals(3, accounts.accounts.size)

        with(accounts.accounts[0]) {
            assertEquals("1", id)
            assertEquals("外貨普通(USD)", name)
            assertEquals("Test Bank", institution)
            assertEquals("USD", currency)
            assertEquals(BigDecimal("22.5"), current_balance)
            assertEquals(BigDecimal("2306.0"), current_balance_in_base)
        }
    }

    @Test
    fun shouldMapTransactionsFromJSONDataToDomainData() {
        val jsonObject = LocalJSONFileLoader(appContext).getJSONObject("json/transactions_2.json")
        val transactions =
            mapTransactionsFromDataToDomain(jsonObject)

        assertEquals(5, transactions.size)

        with(transactions[0]) {
            assertEquals("21", id)
            assertEquals("2", account_id)
            assertEquals(BigDecimal("-442.0"), amount)
            assertEquals("112", category_id)
            assertEquals("2017-05-26T00:00:00+09:00", date)
            assertEquals("スターバックス 原宿店", description)
        }
    }

    @Test
    fun shouldGetTotalBalanceFromAccounts() {
        val jsonObject = LocalJSONFileLoader(appContext).getJSONObject("json/accounts.json")
        val accounts = mapAccountsFromDataToDomain(jsonObject)

        assertEquals(
            "3057.5",
            getTotalBalanceFromAccounts(accounts.accounts)
        )
    }
}
