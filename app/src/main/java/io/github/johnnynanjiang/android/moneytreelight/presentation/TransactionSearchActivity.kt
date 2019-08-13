package io.github.johnnynanjiang.android.moneytreelight.presentation

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.airbnb.mvrx.BaseMvRxActivity
import io.github.johnnynanjiang.android.moneytreelight.R

class TransactionSearchActivity : BaseMvRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_search)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            System.out.println("Search query: $query")
        }
    }
}
