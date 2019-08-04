package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.app.MTLApplication
import io.github.johnnynanjiang.android.moneytreelight.data.TransactionsRepository
import java.math.BigDecimal
import io.reactivex.schedulers.Schedulers

data class Transaction(
    val id: String,
    val account_id: String,
    val amount: BigDecimal,
    val category_id: String,
    val date: String,
    val description: String
)

data class TransactionsState(val transactions: Async<List<Transaction>> = Uninitialized) : MvRxState

class TransactionsViewModel(state: TransactionsState, private val transactionsRepository: TransactionsRepository) :
    BaseMvRxViewModel<TransactionsState>(state, debugMode = true) {

    init {
        getTransactionsForAccount()
    }

    companion object : MvRxViewModelFactory<TransactionsViewModel, TransactionsState> {
        @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: TransactionsState): TransactionsViewModel? {
            return TransactionsViewModel(state, viewModelContext.app<MTLApplication>().localTransactionsRepository)
        }
    }

    private fun getTransactionsForAccount() = transactionsRepository.getTransactionsForAccount()
        .subscribeOn(Schedulers.io())
        .execute { copy(transactions = it) }
}