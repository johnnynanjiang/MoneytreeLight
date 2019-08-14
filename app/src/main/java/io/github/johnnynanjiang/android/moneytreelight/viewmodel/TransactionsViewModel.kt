package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.app.MTLApplication
import io.github.johnnynanjiang.android.moneytreelight.data.TransactionsRepository
import io.github.johnnynanjiang.android.moneytreelight.data.mapTransactionsFromDataToDomain
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionItemView
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionSectionHeaderView
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionView
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

private val EMPTY_LIST: List<TransactionView> = listOf()

data class TransactionsState(
    val transactionsRequest: Async<List<TransactionView>> = Uninitialized,
    val transactions: List<TransactionView> = EMPTY_LIST
) : MvRxState

class TransactionsViewModel(state: TransactionsState, private val transactionsRepository: TransactionsRepository) :
    BaseMvRxViewModel<TransactionsState>(state, debugMode = true) {

    companion object : MvRxViewModelFactory<TransactionsViewModel, TransactionsState> {
        private const val LIST_HEADER_OFFSET = 1

        @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: TransactionsState): TransactionsViewModel? {
            return TransactionsViewModel(state, viewModelContext.app<MTLApplication>().localTransactionsRepository)
        }
    }

    fun getTransactionsForAccount(accountId: String) =
        transactionsRepository.getTransactionsForAccount(accountId)
            .subscribeOn(Schedulers.io())
            .map { mapTransactionFromDataToPresentation(it) }
            .execute { copy(transactionsRequest = it, transactions = it() ?: EMPTY_LIST) }

    fun search(query: String) {
        withState {
            val searchResults = it.transactionsRequest()?.filter { transaction ->
                when (transaction) {
                    is TransactionItemView -> transaction.description.contains(query)
                            || transaction.amount.contains(query)
                    is TransactionSectionHeaderView -> false
                    else -> false
                }
            }

            setState { copy(transactions = searchResults ?: EMPTY_LIST) }
        }
    }

    fun closeSearch() =
        setState { copy(transactions = transactionsRequest() ?: EMPTY_LIST) }

    fun deleteItemAt(position: Int) =
        setState { copy(transactions = transactions.filterIndexed { index, _ -> index != (position - LIST_HEADER_OFFSET) }) }

    private fun mapTransactionFromDataToPresentation(jsonObject: JSONObject): List<TransactionView> =
        mapTransactionsFromDomainToPresentation(
            mapTransactionsFromDataToDomain(jsonObject)
        )
}