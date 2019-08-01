package io.github.johnnynanjiang.android.moneytreelight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import io.github.johnnynanjiang.android.moneytreelight.R

class AccountsFragment : BaseMvRxFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun invalidate() {

    }
}