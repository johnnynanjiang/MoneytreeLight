package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import io.github.johnnynanjiang.android.moneytreelight.domain.Account
import io.github.johnnynanjiang.android.moneytreelight.domain.Transaction
import io.github.johnnynanjiang.android.moneytreelight.util.DateUtil
import io.github.johnnynanjiang.android.moneytreelight.presentation.accounts.AccountItemView
import io.github.johnnynanjiang.android.moneytreelight.presentation.accounts.AccountSectionHeaderView
import io.github.johnnynanjiang.android.moneytreelight.presentation.accounts.AccountView
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionItemView
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionSectionHeaderView
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionView
import java.util.*
import kotlin.Comparator

fun mapAccountSectionHeaderForPresentation(title: String): AccountSectionHeaderView =
    AccountSectionHeaderView(title = title)

fun mapAccountFromDomainToPresentation(account: Account): AccountItemView =
    with(account) {
        AccountItemView(
            id = id,
            name = name,
            currency = currency,
            balance = current_balance.toString()
        )
    }

fun mapAccountsFromDomainToPresentation(accounts: List<Account>): List<AccountView> {
    val accountMap = mapAccountListToHashMap(accounts)
    val accountViews = mutableListOf<AccountView>()

    accountMap.keys.forEach {
        accountViews.add(mapAccountSectionHeaderForPresentation(title = it))

        (accountMap[it] as List<Account>).sortedBy { it.name }.forEach {
            accountViews.add(mapAccountFromDomainToPresentation(it))
        }
    }

    return accountViews
}

fun mapTransactionSectionHeaderForPresentation(title: String): TransactionSectionHeaderView =
    TransactionSectionHeaderView(title = title)

fun mapTransactionFromDomainToPresentation(transaction: Transaction): TransactionItemView =
    with(transaction) {
        TransactionItemView(
            id = id,
            amount = amount.toString(),
            category = category_id,
            date = DateUtil.getDayAsString(date),
            description = description
        )
    }

fun mapTransactionsFromDomainToPresentation(transactions: List<Transaction>): List<TransactionView> {
    val transactionMap = mapTransactionListToHashMap(transactions)
    val transactionViews = mutableListOf<TransactionView>()

    transactionMap.keys.forEach {
        transactionViews.add(mapTransactionSectionHeaderForPresentation(title = DateUtil.getMonthAndYearAsString(it)))

        (transactionMap[it] as List<Transaction>).sortedByDescending { it.date }.forEach {
            transactionViews.add(mapTransactionFromDomainToPresentation(it))
        }
    }

    return transactionViews
}

private fun mapAccountListToHashMap(accounts: List<Account>): Map<String, List<Account>> {
    val accountHashMap = HashMap<String, MutableList<Account>>()

    accounts.forEach {
        accountHashMap.getOrPut(it.institution) { mutableListOf() }.add(it)
    }

    return accountHashMap.toSortedMap()
}

private fun mapTransactionListToHashMap(transactions: List<Transaction>): Map<Date, List<Transaction>> {
    val transactionHashMap = HashMap<Date, MutableList<Transaction>>()

    transactions.forEach {
        transactionHashMap.getOrPut(DateUtil.getDateWithYearAndMonthOnly(it.date)) { mutableListOf() }.add(it)
    }

    return transactionHashMap.toSortedMap(Comparator<Date> { o1, o2 -> o2?.compareTo(o1) ?: -1 })
}