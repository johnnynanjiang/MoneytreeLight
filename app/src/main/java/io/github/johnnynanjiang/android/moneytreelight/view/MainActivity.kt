package io.github.johnnynanjiang.android.moneytreelight.view

import android.os.Bundle
import com.airbnb.mvrx.BaseMvRxActivity
import io.github.johnnynanjiang.android.moneytreelight.R
import android.view.MenuItem

class MainActivity : BaseMvRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
