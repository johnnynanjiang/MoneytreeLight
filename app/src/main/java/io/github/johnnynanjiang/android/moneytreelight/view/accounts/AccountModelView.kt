package io.github.johnnynanjiang.android.moneytreelight.view.accounts

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import io.github.johnnynanjiang.android.moneytreelight.R
import kotlinx.android.synthetic.main.view_account_item.view.*
import kotlinx.android.synthetic.main.view_account_section_header.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AccountSectionHeaderModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_account_section_header, this, true)
    }

    @TextProp
    fun setTitle(_title: CharSequence) {
        title.text = _title
    }
}

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AccountItemModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_account_item, this, true)
    }

    @TextProp
    fun setName(name: CharSequence) {
        accountName.text = name
    }

    @TextProp
    fun setCurrency(currency: CharSequence) {
        accountCurrency.text = currency
    }

    @TextProp
    fun setBalance(balance: CharSequence) {
        accountBalance.text = balance
    }

    @CallbackProp
    fun setClickListener(listener: View.OnClickListener?) = setOnClickListener(listener)
}