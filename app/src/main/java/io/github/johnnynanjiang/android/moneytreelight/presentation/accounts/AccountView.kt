package io.github.johnnynanjiang.android.moneytreelight.presentation.accounts

abstract class AccountView

class AccountItemView(
    val id: String,
    val name: String,
    val currency: String,
    val balance: String
) : AccountView()

class AccountSectionHeaderView(
    val title: String
) : AccountView()