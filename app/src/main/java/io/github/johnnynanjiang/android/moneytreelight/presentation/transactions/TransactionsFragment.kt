package io.github.johnnynanjiang.android.moneytreelight.presentation.transactions

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.transaction_search, menu)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(activity?.componentName)
        )
    }

    private fun updateData() = withState(viewModel) { state ->
        transactionsController.setData(state.transactions.invoke())
    }
}