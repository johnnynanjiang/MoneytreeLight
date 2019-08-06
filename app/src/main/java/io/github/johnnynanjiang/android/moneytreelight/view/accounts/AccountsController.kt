package io.github.johnnynanjiang.android.moneytreelight.view.accounts

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.Typed2EpoxyController
import io.github.johnnynanjiang.android.moneytreelight.R
import io.github.johnnynanjiang.android.moneytreelight.view.transactions.TransactionsFragment

class AccountsController(private val fragment: Fragment) : Typed2EpoxyController<List<AccountView>, Boolean>() {
    override fun buildModels(accounts: List<AccountView>?, loadingMore: Boolean?) {
        accountListHeaderModelView {
            id("id")
            total("JPY2,778")
        }

        accounts?.forEach {
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