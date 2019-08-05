package io.github.johnnynanjiang.android.moneytreelight.view.accounts

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.Typed2EpoxyController
import io.github.johnnynanjiang.android.moneytreelight.R
import io.github.johnnynanjiang.android.moneytreelight.view.transactions.TransactionsFragment
import io.github.johnnynanjiang.android.moneytreelight.domain.Account

class AccountsController(private val fragment: Fragment) : Typed2EpoxyController<List<Account>, Boolean>() {
    override fun buildModels(accounts: List<Account>?, loadingMore: Boolean?) {
        accounts?.forEach {
            accountItemModelView {
                id(it.id)
                name(it.name)
                currency(it.currency)
                balance(it.current_balance.toString())
                clickListener { _ ->
                    fragment.findNavController().navigate(
                        R.id.action_accounts_to_transactions, TransactionsFragment.arg(it.id)
                    )
                }
            }
        }
    }
}