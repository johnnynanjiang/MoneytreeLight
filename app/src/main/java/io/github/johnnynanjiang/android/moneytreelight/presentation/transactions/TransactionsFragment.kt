package io.github.johnnynanjiang.android.moneytreelight.presentation.transactions

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyTouchHelper
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
        setUpSwipe()
    }

    override fun invalidate() = withState(viewModel) {
        progressBar.isVisible = it.transactionsRequest is Loading
        transactionsRecyclerView.isVisible = it.transactionsRequest is Success

        transactionsController.setData(it.transactions)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(activity?.componentName)
        )

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.search(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.closeSearch()
                } else {
                    viewModel.search(newText!!)
                }
                return false
            }
        })

        searchView.setIconifiedByDefault(true)
    }

    private fun setUpSwipe() {
        EpoxyTouchHelper.initSwiping(transactionsRecyclerView)
            .right()
            .withTarget(TransactionItemModelViewModel_::class.java)
            .andCallbacks(object : EpoxyTouchHelper.SwipeCallbacks<TransactionItemModelViewModel_>() {
                override fun onSwipeCompleted(
                    model: TransactionItemModelViewModel_?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
                    viewModel.deleteItemAt(position)
                }
            })
    }
}