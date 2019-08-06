package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import io.github.johnnynanjiang.android.moneytreelight.data.TransactionsRepository
import io.reactivex.Observable
import org.json.JSONObject
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class TransactionsViewModelUnitTest {
    @Test
    fun getTransactionsForAccount() {
        val mockTransactionsRepository: TransactionsRepository = Mockito.mock(TransactionsRepository::class.java)
        val viewModel = TransactionsViewModel(
            state = TransactionsState(),
            transactionsRepository = mockTransactionsRepository
        )
        val accountId = "account id"

        Mockito.`when`(mockTransactionsRepository.getTransactionsForAccount(accountId))
            .thenReturn(Observable.fromCallable { JSONObject() })

        viewModel.getTransactionsForAccount(accountId)

        verify(mockTransactionsRepository).getTransactionsForAccount(accountId)
    }
}