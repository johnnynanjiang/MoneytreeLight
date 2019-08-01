package io.github.johnnynanjiang.android.moneytreelight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import io.github.johnnynanjiang.android.moneytreelight.R
import io.github.johnnynanjiang.android.moneytreelight.viewmodel.Account
import io.github.johnnynanjiang.android.moneytreelight.viewmodel.AccountsViewModel
import kotlinx.android.synthetic.main.fragment_accounts.accountsRecyclerView
import java.math.BigDecimal

class AccountsFragment : BaseMvRxFragment() {
    private val viewModel: AccountsViewModel by activityViewModel()
    private val accountsController = AccountsController()
    private val mockAccounts = listOf(
        Account(
            name = "name1",
            id = "id1",
            institution = "institution1",
            currency = "JPY",
            current_balance = BigDecimal("100.00"),
            current_balance_in_base = BigDecimal("100.00")
        ),
        Account(
            name = "name2",
            id = "id2",
            institution = "institution2",
            currency = "CNY",
            current_balance = BigDecimal("200.00"),
            current_balance_in_base = BigDecimal("200.00")
        )
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        accountsRecyclerView.setController(accountsController)
        accountsController.setData(mockAccounts, true)
    }

    override fun invalidate() = withState(viewModel) { state ->
        accountsController.setData(mockAccounts, true)
    }
}