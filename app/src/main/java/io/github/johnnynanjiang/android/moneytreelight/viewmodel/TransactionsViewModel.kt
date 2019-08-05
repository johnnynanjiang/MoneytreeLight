package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.app.MTLApplication
import io.github.johnnynanjiang.android.moneytreelight.data.TransactionsRepository
import io.github.johnnynanjiang.android.moneytreelight.domain.mapTransactionsFromDataToDomain
import io.github.johnnynanjiang.android.moneytreelight.view.transactions.TransactionView
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

data class TransactionsState(val transactions: Async<List<TransactionView>> = Uninitialized) : MvRxState

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
        .map { mapTransactionFromDataToView(it) }
        .execute { copy(transactions = it) }

    private fun mapTransactionFromDataToView(jsonObject: JSONObject): List<TransactionView> =
        mapTransactionsFromDomainToView(
            mapTransactionsFromDataToDomain(jsonObject)
        )
}