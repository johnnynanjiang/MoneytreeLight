package io.github.johnnynanjiang.android.moneytreelight.view.transactions

import com.airbnb.epoxy.Typed2EpoxyController

class TransactionsController : Typed2EpoxyController<List<TransactionView>, Boolean>() {
    override fun buildModels(transactions: List<TransactionView>?, loadingMore: Boolean?) {
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