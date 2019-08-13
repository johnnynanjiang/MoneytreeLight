package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.app.MTLApplication
import io.github.johnnynanjiang.android.moneytreelight.data.Transaction
import io.github.johnnynanjiang.android.moneytreelight.data.TransactionsRepository
import io.github.johnnynanjiang.android.moneytreelight.data.mapTransactionsFromDataToDomain
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionItemView
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionSectionHeaderView
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionView
import io.reactivex.Observable
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

    fun doSearch(query: String): Observable<List<TransactionView>> =
        Observable.fromCallable { listOf<TransactionView>() }

/*
        Observable.fromCallable {
            var searchResults: List<TransactionView> = listOf()

            withState {
                it.transactions()?.let {
                    searchResults = it.filter { transaction ->
                        when (transaction) {
                            is TransactionItemView -> transaction.description.contains(query)
                                    || transaction.amount.contains(query)
                            is TransactionSectionHeaderView -> false
                            else -> false
                        }
                    }
                }
            }

            searchResults
        }
*/

    fun search(query: String) {
        System.out.println("TransactionsViewModel.search($query)")

        doSearch(query)
            .subscribeOn(Schedulers.io())
            .execute { copy(transactions = it) }
    }

    private fun mapTransactionFromDataToPresentation(jsonObject: JSONObject): List<TransactionView> =
        mapTransactionsFromDomainToPresentation(
            mapTransactionsFromDataToDomain(jsonObject)
        )
}