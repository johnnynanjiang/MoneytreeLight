package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import io.github.johnnynanjiang.android.moneytreelight.domain.Account
import io.github.johnnynanjiang.android.moneytreelight.view.accounts.AccountItemView
import io.github.johnnynanjiang.android.moneytreelight.view.accounts.AccountSectionHeaderView
import io.github.johnnynanjiang.android.moneytreelight.view.accounts.AccountView

fun mapAccountSectionHeaderToView(title: String): AccountSectionHeaderView =
    AccountSectionHeaderView(title = title)

fun mapAccountFromDomainToView(account: Account): AccountItemView =
    with(account) {
        AccountItemView(
            id = id,
            name = name,
            currency = currency,
            balance = current_balance.toString()
        )
    }

fun mapAccountsFromDomainToView(accounts: List<Account>): List<AccountView> {
    val accountMap = mapAccountListToHashMap(accounts)
    val accountModelViews = mutableListOf<AccountView>()

    for (key in accountMap.keys) {
        accountModelViews.add(mapAccountSectionHeaderToView(title = key))

        val accounts = (accountMap[key] as List<Account>).sortedBy { it.name }
        for (account in accounts) {
            accountModelViews.add(mapAccountFromDomainToView(account))
        }
    }

    return accountModelViews
}

private fun mapAccountListToHashMap(accounts: List<Account>): Map<String, List<Account>> {
    val accountHashMap = HashMap<String, MutableList<Account>>()

    for (account in accounts) {
        accountHashMap.getOrPut(account.institution) { mutableListOf() }.add(account)
    }

    return accountHashMap.toSortedMap()
}