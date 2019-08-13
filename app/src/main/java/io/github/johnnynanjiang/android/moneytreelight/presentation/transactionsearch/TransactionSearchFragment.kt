package io.github.johnnynanjiang.android.moneytreelight.presentation.transactionsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import io.github.johnnynanjiang.android.moneytreelight.R
import io.github.johnnynanjiang.android.moneytreelight.viewmodel.TransactionsViewModel

class TransactionSearchFragment : BaseMvRxFragment() {
    private val viewModel: TransactionsViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transaction_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        withState(viewModel) {
            it.transactions
        }
    }

    override fun invalidate() {
    }
}
