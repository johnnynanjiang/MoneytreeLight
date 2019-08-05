package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import io.github.johnnynanjiang.android.moneytreelight.domain.Account
import io.github.johnnynanjiang.android.moneytreelight.domain.Transaction
import io.github.johnnynanjiang.android.moneytreelight.view.accounts.AccountItemView
import io.github.johnnynanjiang.android.moneytreelight.view.accounts.AccountSectionHeaderView
import org.junit.Test

import org.junit.Assert.*
import java.math.BigDecimal

class ViewDataMapperUnitTest {
    @Test
    fun mapAccountSectionHeaderToView() {
        assertEquals("account section header", mapAccountSectionHeaderToView(title = "account section header").title)
    }

    @Test
    fun mapAccountFromDomainToView() {
        val accountItemView = mapAccountFromDomainToView(
            Account(
                id = "1",
                name = "name 1",
                institution = "institution 1",
                currency = "USD",
                current_balance = BigDecimal("1"),
                current_balance_in_base = BigDecimal("1")
            )
        )

        with(accountItemView) {
            assertEquals("1", id)
            assertEquals("name 1", name)
            assertEquals("USD", currency)
            assertEquals("1", balance)
        }
    }

    @Test
    fun mapAccountsFromDomainToView() {
        val accounts = listOf(
            Account(
                id = "3",
                name = "name 3",
                institution = "institution 3",
                currency = "YUAN",
                current_balance = BigDecimal("3"),
                current_balance_in_base = BigDecimal("3")
            ),
            Account(
                id = "1",
                name = "name 1",
                institution = "institution 1",
                currency = "USD",
                current_balance = BigDecimal("1"),
                current_balance_in_base = BigDecimal("1")
            ),
            Account(
                id = "33",
                name = "name 33",
                institution = "institution 3",
                currency = "YUAN",
                current_balance = BigDecimal("33"),
                current_balance_in_base = BigDecimal("33")
            ),
            Account(
                id = "2",
                name = "name 2",
                institution = "institution 2",
                currency = "AUD",
                current_balance = BigDecimal("2"),
                current_balance_in_base = BigDecimal("2")
            ),
            Account(
                id = "11",
                name = "name 11",
                institution = "institution 1",
                currency = "USD",
                current_balance = BigDecimal("11"),
                current_balance_in_base = BigDecimal("11")
            )
        )

        val accountModelViews = mapAccountsFromDomainToView(accounts)

        assertEquals(8, accountModelViews.size)
        assertEquals("institution 1", (accountModelViews[0] as AccountSectionHeaderView).title)
        assertEquals("1", (accountModelViews[1] as AccountItemView).id)
        assertEquals("11", (accountModelViews[2] as AccountItemView).id)
        assertEquals("institution 2", (accountModelViews[3] as AccountSectionHeaderView).title)
        assertEquals("2", (accountModelViews[4] as AccountItemView).id)
        assertEquals("institution 3", (accountModelViews[5] as AccountSectionHeaderView).title)
        assertEquals("3", (accountModelViews[6] as AccountItemView).id)
        assertEquals("33", (accountModelViews[7] as AccountItemView).id)
    }

    @Test
    fun mapTransactionsFromDomainToView() {
        val transactions = listOf(
            Transaction(
                id = "1",
                account_id = "account id 1",
                amount = BigDecimal("1"),
                category_id = "category id 1",
                date = "2017-05-26T00:00:00+09:00",
                description = "description 1"
            ),
            Transaction(
                id = "2",
                account_id = "account id 2",
                amount = BigDecimal("2"),
                category_id = "category id 2",
                date = "2017-06-26T00:00:00+09:00",
                description = "description 2"
            ),
            Transaction(
                id = "3",
                account_id = "account id 3",
                amount = BigDecimal("3"),
                category_id = "category id 3",
                date = "2017-06-27T00:00:00+09:00",
                description = "description 3"
            ),
            Transaction(
                id = "4",
                account_id = "account id 4",
                amount = BigDecimal("4"),
                category_id = "category id 4",
                date = "2018-01-01T00:00:00+09:00",
                description = "description 4"
            )
        )

        val transactionModelViews = mapTransactionsFromDomainToView(transactions)
    }
}
