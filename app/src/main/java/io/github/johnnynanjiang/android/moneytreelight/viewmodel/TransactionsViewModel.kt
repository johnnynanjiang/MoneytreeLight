package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.app.MTLApplication
import io.github.johnnynanjiang.android.moneytreelight.data.TransactionsRepository
import io.github.johnnynanjiang.android.moneytreelight.data.mapTransactionsFromDataToDomain
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionView
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

data class TransactionsState(
    val transactions: Async<List<TransactionView>> = Uninitialized
) : MvRxState

class TransactionsViewModel(state: TransactionsState, private val transactionsRepository: TransactionsRepository) :
    BaseMvRxViewModel<TransactionsState>(state, debugMode = true) {

    companion object : MvRxViewModelFactory<TransactionsViewModel, TransactionsState> {
        @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: TransactionsState): TransactionsViewModel? {
            return TransactionsViewModel(state, viewModelContext.app<MTLApplication>().localTransactionsRepository)
        }
    }

    fun getTransactionsForAccount(accountId: String) =
        transactionsRepository.getTransactionsForAccount(accountId)
            .subscribeOn(Schedulers.io())
            .map { mapTransactionFromDataToPresentation(it) }
            .execute { copy(transactions = it) }

    private fun mapTransactionFromDataToPresentation(jsonObject: JSONObject): List<TransactionView> =
        mapTransactionsFromDomainToPresentation(
            mapTransactionsFromDataToDomain(jsonObject)
        )
}