package io.github.johnnynanjiang.android.moneytreelight.presentation.accounts

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.TypedEpoxyController
import io.github.johnnynanjiang.android.moneytreelight.R
import io.github.johnnynanjiang.android.moneytreelight.presentation.transactions.TransactionsFragment
import io.github.johnnynanjiang.android.moneytreelight.viewmodel.AccountsState

class AccountsController(private val fragment: Fragment) : TypedEpoxyController<AccountsState>() {
    override fun buildModels(state: AccountsState) {
        accountListHeaderModelView {
            id("id")
            total(state.totalBalance)
        }

        state.accounts()?.forEach {
            when (it) {
                is AccountItemView -> accountItemModelView {
                    id(it.id)
                    name(it.name)
                    currency(it.currency)
                    balance(it.balance)
                    clickListener { _ ->
                        fragment.findNavController().navigate(
                            R.id.action_accounts_to_transactions, TransactionsFragment.arg(it.id)
                        )
                    }
                }

                is AccountSectionHeaderView -> accountSectionHeaderModelView {
                    id(it.title)
                    title(it.title)
                }
            }
        }
    }
}