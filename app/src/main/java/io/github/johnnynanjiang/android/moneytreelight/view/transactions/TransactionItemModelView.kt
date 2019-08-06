package io.github.johnnynanjiang.android.moneytreelight.view.transactions

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import io.github.johnnynanjiang.android.moneytreelight.R
import kotlinx.android.synthetic.main.view_account_list_header.view.*
import kotlinx.android.synthetic.main.view_account_section_header.view.*
import kotlinx.android.synthetic.main.view_transaction_item.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TransactionListHeaderModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_transaction_list_header, this, true)
    }

    @TextProp
    fun setTotal(_total: CharSequence) {
        total.text = _total
    }
}

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TransactionSectionHeaderModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_transaction_section_header, this, true)
    }

    @TextProp
    fun setTitle(_title: CharSequence) {
        title.text = _title
    }
}

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TransactionItemModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_transaction_item, this, true)
    }

    @TextProp
    fun setDate(_date: CharSequence) {
        date.text = _date
    }

    @TextProp
    fun setCategory(_category: CharSequence) {
        category.text = _category
    }

    @TextProp
    fun setAmount(_amount: CharSequence) {
        amount.text = _amount
    }

    @TextProp
    fun setDescription(_description: CharSequence) {
        description.text = _description
    }
}