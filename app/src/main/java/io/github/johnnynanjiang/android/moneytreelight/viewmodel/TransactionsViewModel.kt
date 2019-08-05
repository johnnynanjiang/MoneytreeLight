package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.app.MTLApplication
import io.github.johnnynanjiang.android.moneytreelight.data.TransactionsRepository
import io.github.johnnynanjiang.android.moneytreelight.domain.Transaction
import io.github.johnnynanjiang.android.moneytreelight.domain.mapTransactionsFromDataToDomain
import io.reactivex.schedulers.Schedulers

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
        .map { mapTransactionsFromDataToDomain(it) }
        .execute { copy(transactions = it) }
}