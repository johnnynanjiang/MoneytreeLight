package io.github.johnnynanjiang.android.moneytreelight.presentation.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import io.github.johnnynanjiang.android.moneytreelight.R
import io.github.johnnynanjiang.android.moneytreelight.viewmodel.TransactionsViewModel
import kotlinx.android.synthetic.main.fragment_transactions.*

class TransactionsFragment : BaseMvRxFragment() {
    private val viewModel: TransactionsViewModel by activityViewModel()
    private val transactionsController = TransactionsController()
    private val accountId: String by args()

    companion object {
        fun arg(accountId: String): Bundle {
            val args = Bundle()
            args.putString(MvRx.KEY_ARG, accountId)
            return args
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getTransactionsForAccount(accountId)
        transactionsRecyclerView.setController(transactionsController)
        updateData()
    }

    override fun invalidate() = withState(viewModel) { _ ->
        updateData()
    }

    private fun updateData() = withState(viewModel) { state ->
        transactionsController.setData(state.transactions.invoke())
    }
}