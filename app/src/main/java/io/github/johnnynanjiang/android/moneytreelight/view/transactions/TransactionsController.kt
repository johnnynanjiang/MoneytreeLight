package io.github.johnnynanjiang.android.moneytreelight.view.transactions

import androidx.fragment.app.Fragment
import com.airbnb.epoxy.Typed2EpoxyController
import io.github.johnnynanjiang.android.moneytreelight.domain.Transaction

class TransactionsController(private val fragment: Fragment) : Typed2EpoxyController<List<Transaction>, Boolean>() {
    override fun buildModels(transactions: List<Transaction>?, loadingMore: Boolean?) {
        transactions?.forEach {
            transactionRow {
                id(it.id)
                date(it.date)
                category(it.category_id)
                description(it.description)
                amount(it.amount.toString())
            }
        }
    }
}