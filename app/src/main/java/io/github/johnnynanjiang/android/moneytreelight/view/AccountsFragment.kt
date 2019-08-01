package io.github.johnnynanjiang.android.moneytreelight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import io.github.johnnynanjiang.android.moneytreelight.R
import io.github.johnnynanjiang.android.moneytreelight.viewmodel.AccountsViewModel
import kotlinx.android.synthetic.main.fragment_accounts.accountsRecyclerView

class AccountsFragment : BaseMvRxFragment() {
    private val viewModel: AccountsViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        accountsRecyclerView.setControllerAndBuildModels(AccountsController())
    }

    override fun invalidate() = withState(viewModel) { state ->
        accountsRecyclerView.requestModelBuild()
    }
}