package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import io.github.johnnynanjiang.android.moneytreelight.domain.Account
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
}
