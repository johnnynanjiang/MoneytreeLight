package io.github.johnnynanjiang.android.moneytreelight.viewmodel

import io.github.johnnynanjiang.android.moneytreelight.data.AccountsRepository
import io.reactivex.Observable
import org.json.JSONObject
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class AccountsViewModelUnitTest {
    @Test
    fun shouldGetAccounts() {
        val mockAccountsRepository: AccountsRepository = Mockito.mock(AccountsRepository::class.java)
        val viewModel = AccountsViewModel(
            state = AccountsState(),
            accountsRepository = mockAccountsRepository
        )

        Mockito.`when`(mockAccountsRepository.getAccounts())
            .thenReturn(Observable.fromCallable { JSONObject() })

        viewModel.getAccounts()

        verify(mockAccountsRepository).getAccounts()
    }
}