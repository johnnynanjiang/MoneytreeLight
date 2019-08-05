package io.github.johnnynanjiang.android.moneytreelight.view.transactions

abstract class TransactionView

class TransactionItemView(
    val id: String,
    val date: String,
    val category: String,
    val description: String,
    val amount: String
) : TransactionView()

class TransactionSectionHeaderView(
    val title: String
) : TransactionView()