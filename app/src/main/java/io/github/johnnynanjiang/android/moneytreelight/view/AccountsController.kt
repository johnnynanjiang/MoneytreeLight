package io.github.johnnynanjiang.android.moneytreelight.view

import com.airbnb.epoxy.Typed2EpoxyController
import io.github.johnnynanjiang.android.moneytreelight.viewmodel.Account

class AccountsController : Typed2EpoxyController<List<Account>, Boolean>() {
    override fun buildModels(accounts: List<Account>?, loadingMore: Boolean?) {
        accounts?.forEach {
            accountRow {
                id(it.id)
                name(it.name)
                currency(it.currency)
                balance(it.current_balance.toString())
            }
        }
    }
}