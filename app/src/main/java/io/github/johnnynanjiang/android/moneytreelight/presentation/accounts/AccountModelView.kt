package io.github.johnnynanjiang.android.moneytreelight.presentation.accounts

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
import kotlinx.android.synthetic.main.view_account_list_header.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AccountListHeaderModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_account_list_header, this, true)
    }

    @TextProp
    fun setTotal(_total: CharSequence) {
        total.text = _total
    }
}

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
    fun setName(_name: CharSequence) {
        name.text = _name
    }

    @TextProp
    fun setCurrency(_currency: CharSequence) {
        currency.text = _currency
    }

    @TextProp
    fun setBalance(_balance: CharSequence) {
        balance.text = _balance
    }

    @CallbackProp
    fun setClickListener(listener: View.OnClickListener?) = setOnClickListener(listener)
}