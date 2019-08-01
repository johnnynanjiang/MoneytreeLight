package io.github.johnnynanjiang.android.moneytreelight.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import io.github.johnnynanjiang.android.moneytreelight.R
import io.github.johnnynanjiang.android.moneytreelight.viewmodel.Account
import kotlinx.android.synthetic.main.row_account.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AccountRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.row_account, this, true)
    }

    @ModelProp
    fun setAccount(account: Account) {
        accountName.text = account.name
        accountCurrency.text = account.currency
        accountBalance.text = account.current_balance.toString()
    }
}