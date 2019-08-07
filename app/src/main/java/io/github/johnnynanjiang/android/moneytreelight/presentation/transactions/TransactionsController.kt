package io.github.johnnynanjiang.android.moneytreelight.presentation.transactions

import com.airbnb.epoxy.TypedEpoxyController

class TransactionsController : TypedEpoxyController<List<TransactionView>>() {
    override fun buildModels(transactions: List<TransactionView>?) {
        transactionListHeaderModelView {
            id("id")
            total("JPY1,234")
        }

        transactions?.forEach {
            when (it) {
                is TransactionItemView -> transactionItemModelView {
                    id(it.id)
                    date(it.date)
                    amount(it.amount)
                    category(it.category)
                    description(it.description)
                }

                is TransactionSectionHeaderView -> transactionSectionHeaderModelView {
                    id(it.title)
                    title(it.title)
                }
            }
        }
    }
}